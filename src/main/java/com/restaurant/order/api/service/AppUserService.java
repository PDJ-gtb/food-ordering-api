package com.restaurant.order.api.service;

import com.restaurant.order.api.dto.request.AppUserRequest;
import com.restaurant.order.api.dto.response.AppUserResponse;
import com.restaurant.order.api.entity.AppUser;
import com.restaurant.order.api.entity.Role;
import com.restaurant.order.api.exceptions.ResourceNotFoundException;
import com.restaurant.order.api.repository.AppUserRepository;
import com.restaurant.order.api.repository.RoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AppUserService {

    private final PasswordEncoder passwordEncoder;
    private final AppUserRepository appUserRepository;

    private final RoleRepository roleRepository;

    public AppUserService(AppUserRepository appUserRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder){
        this.appUserRepository = appUserRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AppUserResponse newUser(AppUserRequest appUserRequest){
        AppUser createdUser = appUserRepository.save(fromRequestToAppUser(appUserRequest));
        return fromAppUserToResponse(createdUser);

    }

    public List<AppUserResponse> getAllUsers(){
        List<AppUserResponse> userList = new ArrayList<>();
        for (AppUser user : appUserRepository.findAll()){
            userList.add(fromAppUserToResponse(user));
        }
        return userList;

    }

    public AppUserResponse getUserById(Integer id){
        AppUser user = appUserRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User with ID -> " + id + " NOT FOUND"));
        return fromAppUserToResponse(user);
    }

    private AppUser fromRequestToAppUser(AppUserRequest appUserRequest){
        AppUser user = new AppUser();

        user.setUsername(appUserRequest.getUsername());
        user.setPassword(passwordEncoder.encode(appUserRequest.getPassword()));
        user.setEmail(appUserRequest.getEmail());
        user.setEnabled(appUserRequest.getEnabled());

        Set<Integer> roleIds = appUserRequest.getRoleIds();
        Set<Role> roles = new HashSet<>();

        for (Integer roleId : roleIds){
           Role role = roleRepository.findById(roleId).orElseThrow(()->
                    new ResourceNotFoundException("Role with ID -> " + roleId + " NOT FOUND"));
            roles.add(role);

        }
        user.setRoles(roles);

        return user;
    }

    private AppUserResponse fromAppUserToResponse(AppUser user){
        AppUserResponse response = new AppUserResponse();

        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setEnabled(user.getEnabled());

        Set<String> rolesString = new HashSet<>();
        Set<Role> rolesUser = user.getRoles();

        for (Role role : rolesUser){
            rolesString.add(role.getName());
        }
        response.setRoles(rolesString);

        return response;


    }
}
