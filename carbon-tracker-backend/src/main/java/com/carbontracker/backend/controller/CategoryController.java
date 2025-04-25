package com.carbontracker.backend.controller;

import com.carbontracker.backend.entity.Category;
import com.carbontracker.backend.service.CategoryService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category savedCategory = categoryService.saveCategory(category);
        return ResponseEntity.ok(savedCategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        category.setCategoryId(id);
        Category updatedCategory = categoryService.updateCategory(category);
        return ResponseEntity.ok(updatedCategory);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        Category category = categoryService.getCategoryById(id);
        return ResponseEntity.ok(category);
    }
    
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    // Activate category
    @PostMapping("/{id}/activate")
    public ResponseEntity<String> activateCategory(@PathVariable Long id) {
        try {
            categoryService.activateCategory(id);
            return ResponseEntity.ok("Category activated successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("Category not found");
        }
    }

    // Deactivate category
    @PostMapping("/{id}/deactivate")
    public ResponseEntity<String> deactivateCategory(@PathVariable Long id) {
        try {
            categoryService.deactivateCategory(id);
            return ResponseEntity.ok("Category deactivated successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("Category not found");
        }
    }
}
