package com.project.insurancems;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "test";
        String encodedPassword = encoder.encode(rawPassword);
        System.out.printf(encodedPassword);
    }
}
