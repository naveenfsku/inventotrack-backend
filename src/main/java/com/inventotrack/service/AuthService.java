package com.inventotrack.service;

import com.inventotrack.dto.AuthRequest;
import com.inventotrack.dto.AuthResponse;

import jakarta.servlet.http.HttpSession;

public interface AuthService {

    AuthResponse login(AuthRequest request,
                       HttpSession session);

    void logout(HttpSession session);

    AuthResponse getCurrentUser(HttpSession session);

}