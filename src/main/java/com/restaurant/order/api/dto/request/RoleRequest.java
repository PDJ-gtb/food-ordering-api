package com.restaurant.order.api.dto.request;


import jakarta.validation.constraints.NotBlank;

public class RoleRequest {

    @NotBlank
    private String name;

    public RoleRequest(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
