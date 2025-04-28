package com.carbontracker.backend.controller;

import com.carbontracker.backend.entity.SubCategory;
import com.carbontracker.backend.service.SubCategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subcategory")
public class SubCategoryController {

    private final SubCategoryService subCategoryService;

    @Autowired
    public SubCategoryController(SubCategoryService subCategoryService) {
        this.subCategoryService = subCategoryService;
    }

    @PutMapping("/create/{categoryId}")
    public ResponseEntity<SubCategory> createSubCategory(@PathVariable Long categoryId, @RequestBody SubCategory subCategory) {
        SubCategory saved = subCategoryService.saveSubCategory(categoryId, subCategory);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SubCategory> updateSubCategory(@PathVariable Long id, @RequestBody SubCategory subCategory) {
        SubCategory updated = subCategoryService.updateSubCategory(id, subCategory);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubCategory> getSubCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(subCategoryService.getSubCategoryById(id));
    }

    @GetMapping
    public ResponseEntity<List<SubCategory>> getAllSubCategories() {
        List<SubCategory> list = subCategoryService.getAllSubCategories();
        return ResponseEntity.ok(list);
    }

    @PostMapping("/{id}/activate")
    public ResponseEntity<String> activateSubCategory(@PathVariable Long id) {
        try {
            subCategoryService.activateSubCategory(id);
            return ResponseEntity.ok("SubCategory activated successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("SubCategory not found");
        }
    }

    @PostMapping("/{id}/deactivate")
    public ResponseEntity<String> deactivateSubCategory(@PathVariable Long id) {
        try {
            subCategoryService.deactivateSubCategory(id);
            return ResponseEntity.ok("SubCategory deactivated successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("SubCategory not found");
        }
    }
}
