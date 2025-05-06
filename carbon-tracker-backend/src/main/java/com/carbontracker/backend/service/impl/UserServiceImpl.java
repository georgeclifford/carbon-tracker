package com.carbontracker.backend.service.impl;

import com.carbontracker.backend.dto.UserRegistrationRequest;
import com.carbontracker.backend.entity.Login;
import com.carbontracker.backend.entity.User;
import com.carbontracker.backend.repository.LoginRepository;
import com.carbontracker.backend.repository.UserRepository;
import com.carbontracker.backend.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User createUser(UserRegistrationRequest request) {
        if (loginRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username Already Exists!");
        }

        Login login = new Login();
        login.setUsername(request.getUsername());
        login.setPassword(passwordEncoder.encode(request.getPassword()));
        login.setRole(request.getRole());
        login.setAccountStatus("ACTIVE");

        login = loginRepository.save(login);

        User user = new User();
        user.setLogin(login);
        user.setFirstName(request.getFirstName());
        user.setMiddleName(request.getMiddleName());
        user.setLastName(request.getLastName());
        user.setDepartment(request.getDepartment());
        user.setDob(request.getDob());
        user.setGender(request.getGender());
        user.setPhone(request.getPhone());
        user.setAddress(request.getAddress());
        user.setDistrict(request.getDistrict());
        user.setState(request.getState());
        user.setPinCode(request.getPinCode());
        user.setRegistrationDate(LocalDate.now());

        return userRepository.save(user);
    }


    @Override
    public User updateUser(Long userId, UserRegistrationRequest request) {
        Optional<User> existingOpt = userRepository.findById(userId);
        if (existingOpt.isEmpty()) {
            throw new IllegalArgumentException("User Not Found!");
        }
        
        User existingUser = existingOpt.get();
        
//        Login existingLogin = existingUser.getLogin();
//        existingLogin.setPassword(passwordEncoder.encode(request.getPassword()));

        existingUser.setFirstName(request.getFirstName());
        existingUser.setMiddleName(request.getMiddleName());
        existingUser.setLastName(request.getLastName());
        existingUser.setDepartment(request.getDepartment());
        existingUser.setDob(request.getDob());
        existingUser.setGender(request.getGender());
        existingUser.setPhone(request.getPhone());
        existingUser.setAddress(request.getAddress());
        existingUser.setDistrict(request.getDistrict());
        existingUser.setState(request.getState());
        existingUser.setPinCode(request.getPinCode());
        
//        loginRepository.save(existingLogin);

        return userRepository.save(existingUser);
    }

    @Override
    public void deactivateUser(Long userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("User Not Found!"));

        Login login = user.getLogin();
        login.setAccountStatus("INACTIVE");
        loginRepository.save(login);
    }

    @Override
    public void activateUser(Long userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("User Not Found!"));

        Login login = user.getLogin();
        login.setAccountStatus("ACTIVE");
        loginRepository.save(login);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }
    
    public User getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("No Authenticated User Found");
        }
        String username = authentication.getName(); // Get the username from Authentication object
        Optional<Login> loginOpt = loginRepository.findByUsername(username);
        Login login = loginOpt.get();
        User user = login.getUser();
        return user;
    }

    public Long getLoggedInUserId() {
        User user = getLoggedInUser();
        return user.getUserId();
    }
}
