package com.inventotrack.util;

import com.inventotrack.enums.UserRole;
import jakarta.servlet.http.HttpSession;

public final class SessionUtil {

    private SessionUtil() {
    }

    public static void createUserSession(HttpSession session,
                                         Long userId,
                                         String username,
                                         UserRole role) {

        session.setAttribute("userId", userId);
        session.setAttribute("username", username);
        session.setAttribute("role", role);
    }

    public static void invalidate(HttpSession session) {

        if (session != null) {
            session.invalidate();
        }
    }

    public static Long getUserId(HttpSession session) {
        return (Long) session.getAttribute("userId");
    }

    public static UserRole getUserRole(HttpSession session) {
        return (UserRole) session.getAttribute("role");
    }

    public static boolean isLoggedIn(HttpSession session) {

        return session != null &&
                session.getAttribute("userId") != null;
    }

}