package com.restaurant.order.api.controller;

import com.restaurant.order.api.dto.request.RoleRequest;
import com.restaurant.order.api.dto.response.RoleResponse;
import com.restaurant.order.api.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService){
        this.roleService = roleService;
    }

    @GetMapping
    public List<RoleResponse> getAllRoles(){
        return roleService.getAllRoles();
    }

    @GetMapping("/{id}")
    public RoleResponse getRoleById(@PathVariable Integer id){
        return roleService.getRoleById(id);
    }

    @PostMapping
    public ResponseEntity<RoleResponse> newRole(@Valid @RequestBody RoleRequest request){

        return ResponseEntity.status(HttpStatus.CREATED).body(roleService.newRole(request));
    }
}
