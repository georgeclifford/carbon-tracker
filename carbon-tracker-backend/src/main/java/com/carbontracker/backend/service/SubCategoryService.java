package com.carbontracker.backend.service;

import com.carbontracker.backend.entity.SubCategory;
import java.util.List;

public interface SubCategoryService {

    SubCategory saveSubCategory(Long categoryId, SubCategory subCategory);
    SubCategory updateSubCategory(Long id, SubCategory updatedSubCategory);
    List<SubCategory> getAllSubCategories();
    SubCategory getSubCategoryById(Long id);
    void activateSubCategory(Long subCategoryId);
    void deactivateSubCategory(Long subCategoryId);
}
