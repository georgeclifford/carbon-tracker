package com.carbontracker.backend.service.impl;

import com.carbontracker.backend.entity.Category;
import com.carbontracker.backend.repository.CategoryRepository;
import com.carbontracker.backend.service.CategoryService;
import com.carbontracker.backend.service.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
    private CategoryRepository categoryRepository;
	
	@Autowired
    private UserService userService;

    public CategoryServiceImpl(CategoryRepository categoryRepository, UserService userService) {
        this.categoryRepository = categoryRepository;
        this.userService = userService;
    }

    @Override
    public Category saveCategory(Category category) {
        // Set the logged-in user ID
        category.setUser(userService.getLoggedInUser());
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Long id, Category updatedCategory) {
        Category existing = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        existing.setCategoryName(updatedCategory.getCategoryName());
        existing.setTimestamp(updatedCategory.getTimestamp());
        existing.setStatus(updatedCategory.getStatus());
        existing.setUser(userService.getLoggedInUser());

        return categoryRepository.save(existing);
    }


    @Override
    public Category getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("Category not found"));
    }
    
    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void activateCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        category.setStatus("ACTIVE");
        categoryRepository.save(category);
    }

    @Override
    public void deactivateCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        category.setStatus("INACTIVE");
        categoryRepository.save(category);
    }
}
