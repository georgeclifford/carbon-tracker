package com.carbontracker.backend.repository;

import com.carbontracker.backend.entity.SubCategory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
	Optional<SubCategory> findBySubCategoryName(String subCategoryName);
}
