package com.carbontracker.backend.service;

import java.util.List;

import com.carbontracker.backend.entity.Category;

public interface CategoryService {
    Category saveCategory(Category category);
    Category updateCategory(Category category);
    Category getCategoryById(Long categoryId);
    List<Category> getAllCategories();
    void activateCategory(Long categoryId);
    void deactivateCategory(Long categoryId);
}
