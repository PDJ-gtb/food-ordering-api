package com.restaurant.order.api.service;

import com.restaurant.order.api.dto.request.DishRequest;
import com.restaurant.order.api.dto.response.DishResponse;
import com.restaurant.order.api.entity.Category;
import com.restaurant.order.api.entity.Dish;
import com.restaurant.order.api.exceptions.ResourceNotFoundException;
import com.restaurant.order.api.repository.CategoryRepository;
import com.restaurant.order.api.repository.DishRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DishServiceTest {
    @Mock
    private DishRepository dishRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private DishService dishService;

    @Test
    void newDish_shouldReturnDishResponse_whenRequestIsValidAndCategoryExists() {
        DishRequest request = new DishRequest();
        request.setName("Pizza Margharita");
        request.setDescription("Simple");
        request.setAvailable(true);
        request.setPrice(BigDecimal.valueOf(10.50));
        request.setCategoryId(1);

        Category category = new Category();
        category.setId(1);
        category.setName("Pizza");
        category.setDescription("Tasty Pizzas");

        Dish dish = new Dish();
        dish.setId(1);
        dish.setName(request.getName());
        dish.setDescription(request.getDescription());
        dish.setPrice(request.getPrice());
        dish.setAvailable(request.getAvailable());
        dish.setCategory(category);

        when(categoryRepository.findById(1)).thenReturn(Optional.of(category));
        when(dishRepository.save(any(Dish.class))).thenReturn(dish);

        DishResponse response = dishService.newDish(request);

        assertEquals(1,response.getId());
        assertEquals("Pizza Margharita", response.getName());
        assertEquals("Simple", response.getDescription());
        assertTrue(response.getAvailable());
        assertEquals(BigDecimal.valueOf(10.50), response.getPrice());
        assertEquals(1,response.getCategoryId());

        assertEquals("Pizza",response.getCategoryName());


    }

    @Test
    void newDish_shouldThrowException_whenCategoryDoesNotExist(){
        DishRequest request = new DishRequest();
        request.setName("Pizza Margharita");
        request.setDescription("Simple");
        request.setAvailable(true);
        request.setPrice(BigDecimal.valueOf(10.50));
        request.setCategoryId(1);

        when(categoryRepository.findById(1)).thenReturn(Optional.empty());
        ResourceNotFoundException ex =
                assertThrows(ResourceNotFoundException.class, () -> dishService.newDish(request));

        assertEquals("Category ID --> "+ request.getCategoryId()+" NOT FOUND", ex.getMessage());
    }

    @Test
    void updateDish_shouldReturnUpdatedDishResponse_whenDishAndCategoryExist(){
        DishRequest request = new DishRequest();
        request.setName("Pizza Margharita");
        request.setDescription("Old Description");
        request.setAvailable(true);
        request.setPrice(BigDecimal.valueOf(10.50));
        request.setCategoryId(1);

        Category category = new Category();
        category.setId(1);
        category.setName("Pizza");
        category.setDescription("Tasty Pizzas");

        Dish dish = new Dish();
        dish.setId(1);
        dish.setName(request.getName());
        dish.setDescription(request.getDescription());
        dish.setPrice(request.getPrice());
        dish.setAvailable(request.getAvailable());
        dish.setCategory(category);

        when(dishRepository.findById(1)).thenReturn(Optional.of(dish));
        when(categoryRepository.findById(1)).thenReturn(Optional.of(category));
        when(dishRepository.save(any(Dish.class))).thenReturn(dish);

        DishResponse response = dishService.updateDish(request,1);


        assertEquals(1,response.getId());
        assertEquals("Pizza Margharita", response.getName());
        assertEquals("Old Description", response.getDescription());
        assertTrue(response.getAvailable());
        assertEquals(BigDecimal.valueOf(10.50), response.getPrice());
        assertEquals(1,response.getCategoryId());

        assertEquals("Pizza",response.getCategoryName());

    }

    @Test
    void updateDish_shouldThrowException_whenDishDoesNotExist(){
        DishRequest request = new DishRequest();
        request.setName("Pizza Margharita");
        request.setDescription("Old Description");
        request.setAvailable(true);
        request.setPrice(BigDecimal.valueOf(10.50));
        request.setCategoryId(1);

        when(dishRepository.findById(1)).thenReturn(Optional.empty());
        ResourceNotFoundException ex =
                assertThrows(ResourceNotFoundException.class, () -> dishService.updateDish(request,1));
        assertEquals("Dish with ID -> "+ 1 +" NOT FOUND", ex.getMessage());
    }

    @Test
    void updateDish_shouldThrowException_whenCategoryDoesNotExist(){
        DishRequest request = new DishRequest();
        request.setName("Pizza Margharita");
        request.setDescription("Old Description");
        request.setAvailable(true);
        request.setPrice(BigDecimal.valueOf(10.50));
        request.setCategoryId(1);

        Dish dish = new Dish();
        dish.setId(1);
        dish.setName(request.getName());
        dish.setDescription(request.getDescription());
        dish.setPrice(request.getPrice());
        dish.setAvailable(request.getAvailable());


        when(dishRepository.findById(1)).thenReturn(Optional.of(dish));
        when(categoryRepository.findById(1)).thenReturn(Optional.empty());

        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class,() -> dishService.updateDish(request,1));

        assertEquals("Category ID --> "+ request.getCategoryId()+" NOT FOUND",ex.getMessage());

    }

    @Test
    void getDishById_shouldReturnDishResponse_whenDishExists(){
        Category category = new Category();
        category.setId(1);
        category.setName("Pizza");
        category.setDescription("Tasty Pizzas");

        Dish dish = new Dish();
        dish.setId(1);
        dish.setName("Test Name");
        dish.setDescription("Test Description");
        dish.setPrice(BigDecimal.valueOf(16.00));
        dish.setAvailable(true);
        dish.setCategory(category);

        when(dishRepository.findById(1)).thenReturn(Optional.of(dish));

        DishResponse response = dishService.getDishById(1);

        assertEquals(dish.getId(),response.getId());
        assertEquals(dish.getName(),response.getName());
        assertEquals(dish.getDescription(),response.getDescription());
        assertEquals(dish.getPrice(),response.getPrice());
        assertTrue(response.getAvailable());
        assertEquals(category.getId(),response.getCategoryId());
        assertEquals(category.getName(),response.getCategoryName());
    }

    @Test
    void deleteDish_shouldDeleteDish_whenDishExists(){
        Category category = new Category();
        category.setId(1);
        category.setName("Pizza");
        category.setDescription("Tasty Pizzas");

        Dish dish = new Dish();
        dish.setId(1);
        dish.setName("Test Name");
        dish.setDescription("Test Description");
        dish.setPrice(BigDecimal.valueOf(16.00));
        dish.setAvailable(true);
        dish.setCategory(category);

        when(dishRepository.findById(1)).thenReturn(Optional.of(dish));
        dishService.deleteDish(1);
        verify(dishRepository).delete(dish);
    }

}
