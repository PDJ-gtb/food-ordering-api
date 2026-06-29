package com.restaurant.order.api.controller;

import com.restaurant.order.api.dto.request.CategoryRequest;
import com.restaurant.order.api.dto.response.CategoryResponse;
import com.restaurant.order.api.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<CategoryResponse> newCategoryId(@Valid @RequestBody CategoryRequest request){

        CategoryResponse response =  categoryService.newCategory(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // PUT
    @PutMapping ("/{id}")
    public ResponseEntity<CategoryResponse> updateCategory(@PathVariable Integer id, @Valid @RequestBody CategoryRequest request){
        CategoryResponse categoryResponse= categoryService.updateCategory(request,id);
        return ResponseEntity.ok(categoryResponse);


    }
    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Integer id){

       categoryService.deleteCategory(id);
       return ResponseEntity.noContent().build();

    }
}
