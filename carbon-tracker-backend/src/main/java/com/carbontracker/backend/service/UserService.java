package com.carbontracker.backend.service;

import com.carbontracker.backend.dto.UserRegistrationRequest;
import com.carbontracker.backend.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User createUser(UserRegistrationRequest request);

    User updateUser(Long userId, UserRegistrationRequest request);

    void deactivateUser(Long userId);

    void activateUser(Long userId);
    
    List<User> getAllUsers();

    Optional<User> getUserById(Long userId);
    
    User getLoggedInUser();
    
    Long getLoggedInUserId();
}
