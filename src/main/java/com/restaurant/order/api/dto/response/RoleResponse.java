package com.restaurant.order.api.dto.response;

import com.restaurant.order.api.repository.RoleRepository;
import jakarta.validation.constraints.NotBlank;

public class RoleResponse {


    private Integer id;

    private String name;

    public RoleResponse(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
