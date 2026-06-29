package com.restaurant.order.api.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CategoryRequest {
    @NotBlank(message = "Name can't be null or empty")
    @Size(max = 100, message = "name must be at most 100 characters")
    private String name;

    @Size(max=255, message = "description must be at most 255 characters")
    private String description;

    public CategoryRequest() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
