package com.restaurant.order.api.controller;

import com.restaurant.order.api.dto.request.DishRequest;
import com.restaurant.order.api.dto.response.DishResponse;
import com.restaurant.order.api.entity.Dish;
import com.restaurant.order.api.service.DishService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<DishResponse> newDish( @Valid @RequestBody DishRequest request ){

        DishResponse response = dishService.newDish(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /*
    PUT
     */
    @PutMapping("/{id}")
    public ResponseEntity<DishResponse> updateDish(@PathVariable Integer id,  @Valid @RequestBody DishRequest changedDish){
     DishResponse response = dishService.updateDish(changedDish,id);
     return ResponseEntity.ok(response);
    }

    /*
    DELETE
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDish(@PathVariable Integer id){

        dishService.deleteDish(id);
        return ResponseEntity.noContent().build();
    }
}
