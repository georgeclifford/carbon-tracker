package com.carbontracker.backend.service;

import com.carbontracker.backend.dto.UserRegistrationRequest;
import com.carbontracker.backend.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    // Admin: Creates a user + login credentials in one go
    User createUser(UserRegistrationRequest request);

    // Admin/User: Updates the details of a user
    User updateUser(Long userId, UserRegistrationRequest request);

    // Admin: Deactivates the user (prevents login)
    void deactivateUser(Long userId);

    // Admin: Reactivates the user
    void activateUser(Long userId);
    
    // Admin: Get all users
    List<User> getAllUsers();

    // Utility: Get user by ID
    Optional<User> getUserById(Long userId);
}
