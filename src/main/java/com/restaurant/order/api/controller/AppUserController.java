package com.restaurant.order.api.controller;

import com.restaurant.order.api.dto.request.AppUserRequest;
import com.restaurant.order.api.dto.response.AppUserResponse;
import com.restaurant.order.api.service.AppUserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class AppUserController {

    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService){
        this.appUserService = appUserService;
    }

    @GetMapping
    public List<AppUserResponse> getAllUsers(){
        return appUserService.getAllUsers();
    }

    @GetMapping("/{id}")
    public AppUserResponse getUserById(@PathVariable Integer id){
        return appUserService.getUserById(id);
    }

    @PostMapping
    public ResponseEntity<AppUserResponse> newUser(@Valid @RequestBody AppUserRequest request){
        AppUserResponse response = appUserService.newUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
