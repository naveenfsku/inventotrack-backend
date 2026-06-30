package com.inventotrack.test;

import com.inventotrack.util.PasswordUtil;

public class GeneratePassword {

    public static void main(String[] args) {

        String password = "admin123";

        String hash = PasswordUtil.hashPassword(password);

        System.out.println(hash);
    }
}