package com.carbontracker.backend.controller;

import com.carbontracker.backend.dto.UserRegistrationRequest;
import com.carbontracker.backend.entity.User;
import com.carbontracker.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Admin creates a new user
    @PostMapping("/admin")
    public ResponseEntity<User> createUser(@RequestBody UserRegistrationRequest request) {
        try {
            User createdUser = userService.createUser(request);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }


    // Update existing user details (for both admin and govt employees)
    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, 
    		@RequestBody UserRegistrationRequest request) {
        try {
            User updatedUserResult = userService.updateUser(userId, request);
            return new ResponseEntity<>(updatedUserResult, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Admin can deactivate a user
    @PostMapping("/admin/{userId}/deactivate")
    public ResponseEntity<String> deactivateUser(@PathVariable Long userId) {
        try {
            userService.deactivateUser(userId);
            return new ResponseEntity<>("User deactivated successfully", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }

    // Admin can activate a user
    @PostMapping("/admin/{userId}/activate")
    public ResponseEntity<String> activateUser(@PathVariable Long userId) {
        try {
            userService.activateUser(userId);
            return new ResponseEntity<>("User activated successfully", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }
    
    // Admin fetches all users
    @GetMapping("/admin")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


    // Get user details by ID
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        Optional<User> user = userService.getUserById(userId);
        return user.map(ResponseEntity::ok)
                   .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
