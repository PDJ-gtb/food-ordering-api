package com.restaurant.order.api.repository;

import com.restaurant.order.api.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository  extends JpaRepository<Category,Integer> {
    
}
