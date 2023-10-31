package com.inventory.system.services;

import java.util.regex.Pattern;

public class Validator {
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "([a-z]+@[abv|gmail|a-z]+.[bg|com]+)"
    );

    public static boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean isValidPassword(String password) {
        // Check if the password contains at least 1 letter and 7 numbers
        int letterCount = 0;
        int digitCount = 0;

        for (char c : password.toCharArray()) {
            if (Character.isLetter(c)) {
                letterCount++;
            } else if (Character.isDigit(c)) {
                digitCount++;
            }
        }

        return letterCount >= 1 && digitCount >= 7;
    }
    public static boolean isValidUser(String username) {
        // Implement your validation logic here, e.g., check if required fields are not null or empty
        return  username != null;
    }
}
