package com.example.ecommerce.repo;

import com.example.ecommerce.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for Category entity.
 * Provides CRUD operations for Category.
 */
public interface CategoryRepo extends JpaRepository<Category, Long> {
    // Additional query methods can be defined here if needed
}
