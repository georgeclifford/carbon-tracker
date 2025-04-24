package com.carbontracker.backend.repository;

import com.carbontracker.backend.entity.CarbonActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarbonActivityRepository extends JpaRepository<CarbonActivity, Long> {
}
