package com.carbontracker.backend.repository;

import com.carbontracker.backend.entity.Login;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {
    Optional<Login> findByUsername(String username);
}
