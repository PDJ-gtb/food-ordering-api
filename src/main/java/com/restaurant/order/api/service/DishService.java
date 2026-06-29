package com.restaurant.order.api.service;

import com.restaurant.order.api.dto.request.DishRequest;
import com.restaurant.order.api.dto.response.DishResponse;
import com.restaurant.order.api.entity.Category;
import com.restaurant.order.api.exceptions.ResourceNotFoundException;
import com.restaurant.order.api.entity.Dish;
import com.restaurant.order.api.repository.CategoryRepository;
import com.restaurant.order.api.repository.DishRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DishService {

    private final DishRepository dishRepository;
    private final CategoryRepository categoryRepository;

    public DishService(DishRepository dishRepository, CategoryRepository categoryRepository){

    this.dishRepository=dishRepository;
        this.categoryRepository = categoryRepository;
    }
public List<DishResponse> getAllDishes(){
    List<DishResponse> responses = new ArrayList<>();
    for (Dish dish : dishRepository.findAll()){
        responses.add(mapResponse(dish));
    }
    return responses;
}
public DishResponse getDishById(Integer id){
    Dish dish = dishRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Dish with ID -> "+ id+" NOT FOUND"));
    DishResponse response = mapResponse(dish);

    return response;
}
public DishResponse newDish(DishRequest request){
    Dish dish = new Dish();
    dish.setName(request.getName());
    dish.setDescription(request.getDescription());
    dish.setPrice(request.getPrice());
    dish.setAvailable(request.getAvailable());
    Category category = categoryRepository.findById(request.getCategoryId())
            .orElseThrow(() -> new ResourceNotFoundException("Category ID --> "+ request.getCategoryId()+" NOT FOUND"));

    dish.setCategory(category);
    Dish dishSaved = dishRepository.save(dish);
    DishResponse response = mapResponse(dishSaved);

    return response;

}
public DishResponse updateDish(DishRequest request, Integer id){

    Dish oldDish = dishRepository.findById(id).
            orElseThrow(() -> new ResourceNotFoundException("Dish with ID -> "+ id+" NOT FOUND"));

    oldDish.setName(request.getName());
    oldDish.setDescription(request.getDescription());
    oldDish.setPrice(request.getPrice());
    oldDish.setAvailable(request.getAvailable());
    Category category = categoryRepository.findById(request.getCategoryId())
            .orElseThrow(() -> new ResourceNotFoundException("Category ID --> "+ request.getCategoryId()+" NOT FOUND"));

    oldDish.setCategory(category);

    Dish dishSaved = dishRepository.save(oldDish);

    DishResponse response = mapResponse(dishSaved);


    return response;


}
public void deleteDish(Integer id){
    Dish dish = dishRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Dish with ID: " + id + " NOT FOUND."));
    dishRepository.delete(dish);
}

private DishResponse mapResponse(Dish dish){
    DishResponse response = new DishResponse();
    response.setId(dish.getId());
    response.setName(dish.getName());
    response.setDescription(dish.getDescription());
    response.setPrice(dish.getPrice());
    response.setAvailable(dish.getAvailable());
    response.setCategoryId(dish.getCategory().getId());
    response.setCategoryName(dish.getCategory().getName());

    return response;

}

}

