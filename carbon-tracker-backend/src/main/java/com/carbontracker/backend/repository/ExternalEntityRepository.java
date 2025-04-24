package com.carbontracker.backend.repository;

import com.carbontracker.backend.entity.ExternalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExternalEntityRepository extends JpaRepository<ExternalEntity, Long> {
}
