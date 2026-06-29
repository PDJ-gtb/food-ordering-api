package com.restaurant.order.api.controller;

import com.restaurant.order.api.dto.request.DishRequest;
import com.restaurant.order.api.dto.response.DishResponse;
import com.restaurant.order.api.entity.Dish;
import com.restaurant.order.api.service.DishService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/dishes")
public class DishController {

   private final DishService dishService;

    public DishController(DishService dishS){
        this.dishService=dishS;
    }

    /*
    GET
     */
    @GetMapping
    public List<DishResponse> getAllDishes(){

        return dishService.getAllDishes();
    }
    @GetMapping("/{id}")
    public DishResponse getDishById(@PathVariable Integer id){
        return dishService.getDishById(id);
    }
    /*
    POST
     */
    @PostMapping
    public DishResponse newDish( @Valid @RequestBody DishRequest request ){

        return dishService.newDish(request);
    }

    /*
    PUT
     */
    @PutMapping("/{id}")
    public DishResponse updateDish(@PathVariable Integer id,  @Valid @RequestBody DishRequest changedDish){
    return dishService.updateDish(changedDish,id);
    }

    /*
    DELETE
     */
    @DeleteMapping("/{id}")
    public void deleteDish(@PathVariable Integer id){
        dishService.deleteDish(id);
    }
}
