package com.carbontracker.backend.service;

import com.carbontracker.backend.dto.AuthRequest;
import com.carbontracker.backend.dto.AuthResponse;

public interface AuthService {
    AuthResponse authenticate(AuthRequest request);
}
