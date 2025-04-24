package com.carbontracker.backend.service.impl;

import com.carbontracker.backend.dto.AuthRequest;
import com.carbontracker.backend.dto.AuthResponse;
import com.carbontracker.backend.security.JwtService;
import com.carbontracker.backend.security.CustomUserDetailsService;
import com.carbontracker.backend.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtService jwtService;

    @Override
    public AuthResponse authenticate(AuthRequest request) {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
        } catch (DisabledException ex) {
            throw new RuntimeException("Account is disabled");
        } catch (BadCredentialsException ex) {
            throw new RuntimeException("Invalid credentials");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String token = jwtService.generateToken(userDetails);
        return new AuthResponse(token);
    }
}
