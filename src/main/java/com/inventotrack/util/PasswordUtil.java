package com.inventotrack.util;

import org.mindrot.jbcrypt.BCrypt;

public final class PasswordUtil {

    private PasswordUtil() {
    }

    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean verifyPassword(String plainPassword,
                                         String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}