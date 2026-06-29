package com.restaurant.order.api.controller;

import com.restaurant.order.api.dto.request.CategoryRequest;
import com.restaurant.order.api.dto.response.CategoryResponse;
import com.restaurant.order.api.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService=categoryService;
    }

    // GET
    @GetMapping
    public List<CategoryResponse> getAllCategories(){
        return categoryService.getAll();
    }

    @GetMapping("/{id}")
    public CategoryResponse getCategoryById(@PathVariable Integer id){
        return categoryService.getOneById(id);
    }

    // POST
    @PostMapping
    public CategoryResponse newCategoryId(@Valid @RequestBody CategoryRequest request){
        return categoryService.newCategory(request);
    }

    // PUT
    @PutMapping ("/{id}")
    public CategoryResponse updateCategory(@PathVariable Integer id, @Valid @RequestBody CategoryRequest request){
        return categoryService.updateCategory(request,id);
    }
    // DELETE
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Integer id){
        categoryService.deleteCategory(id);
    }
}
