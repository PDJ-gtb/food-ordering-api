package com.restaurant.order.api.service;

import com.restaurant.order.api.dto.request.RoleRequest;
import com.restaurant.order.api.dto.response.RoleResponse;
import com.restaurant.order.api.entity.Role;
import com.restaurant.order.api.exceptions.ResourceNotFoundException;
import com.restaurant.order.api.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }
    public List<RoleResponse> getAllRoles(){
        List<RoleResponse> responses = new ArrayList<>();

        for (Role role : roleRepository.findAll()){
            responses.add(fromRoleToResponse(role));
        }
        return responses;
    }
    public RoleResponse getRoleById(Integer id){
        Role role = roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Role with ID -> " + id + " NOT FOUND"));
        return fromRoleToResponse(role);
    }

    public RoleResponse newRole(RoleRequest request){
        return fromRoleToResponse(roleRepository.save(fromRequestToRole(request)));
    }

    private RoleResponse fromRoleToResponse(Role role){
        RoleResponse response = new RoleResponse();

        response.setId(role.getId());
        response.setName(role.getName());
        return response;
    }
    private Role fromRequestToRole(RoleRequest request){
        Role role = new Role();

        role.setName(request.getName());
        return role;
    }

}
