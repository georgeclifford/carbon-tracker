package com.carbontracker.backend.service.impl;

import com.carbontracker.backend.entity.Category;
import com.carbontracker.backend.entity.SubCategory;
import com.carbontracker.backend.repository.CategoryRepository;
import com.carbontracker.backend.repository.SubCategoryRepository;
import com.carbontracker.backend.service.SubCategoryService;
import com.carbontracker.backend.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {

	@Autowired
    private final SubCategoryRepository subCategoryRepository;
	
	@Autowired
	private final CategoryRepository categoryRepository;
	
	@Autowired
    private final UserService userService;

    @Autowired
    public SubCategoryServiceImpl(SubCategoryRepository subCategoryRepository, CategoryRepository categoryRepository, UserService userService) {
        this.subCategoryRepository = subCategoryRepository;
        this.categoryRepository = categoryRepository;
        this.userService = userService;
    }

    @Override
    public SubCategory saveSubCategory(Long categoryId, SubCategory subCategory) {
        subCategory.setUser(userService.getLoggedInUser());
        Optional<Category> categoryOpt = categoryRepository.findById(categoryId);
        Category category = categoryOpt.get();
        subCategory.setCategory(category);
        return subCategoryRepository.save(subCategory);
    }

    @Override
    public SubCategory updateSubCategory(Long id, SubCategory updatedSubCategory) {
        SubCategory existing = subCategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("SubCategory Not Found!"));

        existing.setCategory(updatedSubCategory.getCategory());
        existing.setSubCategoryName(updatedSubCategory.getSubCategoryName());
        existing.setEfValue(updatedSubCategory.getEfValue());
        existing.setSource(updatedSubCategory.getSource());
        existing.setYear(updatedSubCategory.getYear());
        existing.setTimestamp(updatedSubCategory.getTimestamp());
        existing.setStatus(updatedSubCategory.getStatus());
        existing.setUser(userService.getLoggedInUser());

        return subCategoryRepository.save(existing);
    }

    @Override
    public SubCategory getSubCategoryById(Long id) {
        return subCategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("SubCategory Not Found!"));
    }

    @Override
    public List<SubCategory> getAllSubCategories() {
        return subCategoryRepository.findAll();
    }

    @Override
    public void activateSubCategory(Long id) {
        SubCategory subCategory = subCategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("SubCategory Not Found!"));
        subCategory.setStatus("ACTIVE");
        subCategoryRepository.save(subCategory);
    }

    @Override
    public void deactivateSubCategory(Long id) {
        SubCategory subCategory = subCategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("SubCategory Not Found!"));
        subCategory.setStatus("INACTIVE");
        subCategoryRepository.save(subCategory);
    }
}
