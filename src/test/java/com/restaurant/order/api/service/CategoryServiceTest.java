package com.restaurant.order.api.service;

import com.restaurant.order.api.dto.request.CategoryRequest;
import com.restaurant.order.api.dto.response.CategoryResponse;
import com.restaurant.order.api.entity.Category;
import com.restaurant.order.api.exceptions.ResourceNotFoundException;
import com.restaurant.order.api.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {
    @Mock
    private CategoryRepository categoryRepository;
    @InjectMocks
    private CategoryService categoryService;

    @Test
    void getOneById_shouldReturnCategoryResponse_whenCategoryExists() {
        Category category = new Category();
        category.setId(1);
        category.setName("Burgers");
        category.setDescription("Smash burgers");

        when(categoryRepository.findById(1)).thenReturn(Optional.of(category));

        CategoryResponse response = categoryService.getOneById(1);

        assertEquals(1, response.getId());
        assertEquals("Burgers", response.getName());
        assertEquals("Smash burgers", response.getDescription());
    }

    @Test
    void getOneById_shouldThrowException_whenCategoryDoesNotExist() {
        when(categoryRepository.findById(1)).thenReturn(Optional.empty());

        ResourceNotFoundException ex =
                assertThrows(ResourceNotFoundException.class, () -> categoryService.getOneById(1));

        assertEquals("Category with ID --> 1 NOT FOUND.", ex.getMessage());
    }

    @Test
    void tryToSaveANewCategory_AndIsValid(){
        CategoryRequest request = new CategoryRequest();
        request.setName("Burgers");
        request.setDescription("Smash burgers");

        Category category = new Category();
        category.setId(1);
        category.setName(request.getName());
        category.setDescription(request.getDescription());

        when(categoryRepository.save(any(Category.class))).thenReturn(category);
        CategoryResponse response = categoryService.newCategory(request);

        assertEquals(1,response.getId());
        assertEquals("Burgers", response.getName());
        assertEquals("Smash burgers", response.getDescription());

    }
    @Test
    void updateCategory_shouldReturnUpdatedCategoryResponse_whenCategoryExists(){
        CategoryRequest request = new CategoryRequest();
        request.setName("Burgers");
        request.setDescription("Biggest One");

        Category category = new Category();
        category.setId(1);
        category.setName("Burgers");
        category.setDescription("Smallest one");

        when(categoryRepository.findById(1)).thenReturn(Optional.of(category));
        when(categoryRepository.save(any(Category.class))).thenReturn(category);
        CategoryResponse categoryGiven = categoryService.updateCategory(request,1);

        assertEquals(1,categoryGiven.getId());
        assertEquals("Burgers", categoryGiven.getName());
        assertEquals("Biggest One", categoryGiven.getDescription());

    }

    @Test
    void updateCategoryReturnException(){
        CategoryRequest request = new CategoryRequest();
        request.setName("Burgers");
        request.setDescription("Biggest One");

        when(categoryRepository.findById(1)).thenReturn(Optional.empty());


        ResourceNotFoundException ex =
                assertThrows(ResourceNotFoundException.class, () -> categoryService.updateCategory(request,1));

        assertEquals("Category with ID --> 1 NOT FOUND.", ex.getMessage());
    }
    @Test
    void deleteCategory_shouldDeleteCategory_whenCategoryExists(){
        Category category = new Category();
        category.setId(1);
        category.setName("Burger");
        category.setDescription("Description");
        when(categoryRepository.findById(1)).thenReturn(Optional.of(category));
        categoryService.deleteCategory(1);
        verify(categoryRepository).delete(category);



    }
}
