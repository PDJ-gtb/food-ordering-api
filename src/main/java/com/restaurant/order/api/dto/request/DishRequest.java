package com.restaurant.order.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;


public class DishRequest {

    @NotBlank(message = "Name can't be null or empty")
    private String name;

    @Size(max = 200)
    private String description;
    @NotNull(message = "Price can't be null or empty")
    @Positive(message = "Price must greater than 0")
    private BigDecimal price;
    @NotNull(message = "available can't be null or empty")
    private Boolean available;
    @NotNull(message = "CategoryId can't be null or empty")
    private Integer categoryId;
    
    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }




    public DishRequest(){}

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
