package com.inventotrack;

import com.inventotrack.util.PasswordUtil;

public class PasswordGenerator {

    public static void main(String[] args) {

        System.out.println(
                PasswordUtil.hashPassword("admin123")
        );

    }

}