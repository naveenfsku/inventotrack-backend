package com.inventotrack.service.impl;

import com.inventotrack.dao.UserDAO;
import com.inventotrack.dto.AuthRequest;
import com.inventotrack.dto.AuthResponse;
import com.inventotrack.exception.AuthenticationException;
import com.inventotrack.factory.DAOFactory;
import com.inventotrack.mapper.UserMapper;
import com.inventotrack.model.User;
import com.inventotrack.service.AuthService;
import com.inventotrack.util.JPAUtil;
import com.inventotrack.util.PasswordUtil;
import com.inventotrack.util.SessionUtil;

import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpSession;

public class AuthServiceImpl implements AuthService {

    private final UserDAO userDAO;

    public AuthServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public AuthResponse login(AuthRequest request,
                              HttpSession session) {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            User user = userDAO.findByUsername(
                    em,
                    request.getUsername());

            if (user == null) {

                throw new AuthenticationException(
                        "Invalid username or password");

            }

            boolean valid =
                    PasswordUtil.verifyPassword(
                            request.getPassword(),
                            user.getPasswordHash());

            if (!valid) {

                throw new AuthenticationException(
                        "Invalid username or password");

            }

            SessionUtil.createUserSession(

                    session,

                    user.getId(),

                    user.getUsername(),

                    user.getRole()

            );

            return UserMapper.toAuthResponse(user);

        } finally {

            em.close();

        }

    }

    @Override
    public void logout(HttpSession session) {

        SessionUtil.invalidate(session);

    }

    @Override
    public AuthResponse getCurrentUser(HttpSession session) {

        if (!SessionUtil.isLoggedIn(session)) {

            throw new AuthenticationException(
                    "Please login first");

        }

        EntityManager em = JPAUtil.getEntityManager();

        try {

            User user =
                    userDAO.findById(
                            em,
                            SessionUtil.getUserId(session));

            if (user == null) {

                throw new AuthenticationException(
                        "User not found");

            }

            return UserMapper.toAuthResponse(user);

        } finally {

            em.close();

        }

    }

}