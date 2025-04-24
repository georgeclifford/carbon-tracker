package com.carbontracker.backend.repository;

import com.carbontracker.backend.entity.MonthlyEmissions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonthlyEmissionsRepository extends JpaRepository<MonthlyEmissions, Long> {
}
