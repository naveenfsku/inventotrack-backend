package com.inventotrack.mapper;

import com.inventotrack.dto.AuthResponse;
import com.inventotrack.model.User;

public final class UserMapper {

    private UserMapper() {
    }

    public static AuthResponse toAuthResponse(User user) {

        if (user == null) {
            return null;
        }

        return new AuthResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole()
        );
    }
}