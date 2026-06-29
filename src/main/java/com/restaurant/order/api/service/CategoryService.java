package com.restaurant.order.api.service;

import com.restaurant.order.api.dto.request.CategoryRequest;
import com.restaurant.order.api.dto.response.CategoryResponse;
import com.restaurant.order.api.entity.Category;
import com.restaurant.order.api.exceptions.ResourceNotFoundException;
import com.restaurant.order.api.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository=categoryRepository;
    }
    public List<CategoryResponse> getAll(){

        List<CategoryResponse> categories = new ArrayList<>();
        for (Category category : categoryRepository.findAll()){
            categories.add(mapResponse(category));
        }
        return categories;
    }

    public CategoryResponse getOneById(Integer id){
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category with ID --> "+id+ " NOT FOUND."));

        return mapResponse(category);
    }

    public CategoryResponse newCategory(CategoryRequest request){
        Category category = fromReqToCategory(request);
        Category saved = categoryRepository.save(category);
        return mapResponse(saved);
    }
    public CategoryResponse updateCategory(CategoryRequest request, Integer id){
        Category toChange = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category with ID --> "+id+ " NOT FOUND."));

        toChange.setName(request.getName());
        toChange.setDescription(request.getDescription());

        Category saved = categoryRepository.save(toChange);
        return mapResponse(saved);

    }
    public void deleteCategory(Integer id){
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category with ID --> "+id+ " NOT FOUND."));
        categoryRepository.delete(category);

    }
    private CategoryResponse mapResponse(Category category){
        CategoryResponse response = new CategoryResponse();
        response.setId(category.getId());
        response.setName(category.getName());
        response.setDescription(category.getDescription());


        return response;

    }
    private Category fromReqToCategory(CategoryRequest request){
        Category category = new Category();

        category.setName(request.getName());
        category.setDescription(request.getDescription());

        return category;

    }
}
