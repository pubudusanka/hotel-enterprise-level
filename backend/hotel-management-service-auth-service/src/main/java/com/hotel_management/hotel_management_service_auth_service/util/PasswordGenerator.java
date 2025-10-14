package com.hotel_management.hotel_management_service_auth_service.util;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class PasswordGenerator {

    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARS = "@$#!%^&*";

    private static final String ALL_CHARS = UPPERCASE + LOWERCASE + DIGITS + SPECIAL_CHARS;

    public String generatePassword() {
        StringBuilder password = new StringBuilder(6);
        Random random = new Random();

        password.append(UPPERCASE.charAt(random.nextInt(UPPERCASE.length())));
        password.append(LOWERCASE.charAt(random.nextInt(LOWERCASE.length())));
        password.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
        password.append(SPECIAL_CHARS.charAt(random.nextInt(SPECIAL_CHARS.length())));

        for (int i=4; i < password.length(); i++) {
            password.append(ALL_CHARS.charAt(random.nextInt(SPECIAL_CHARS.length())));
        }

        return shuffleString(password.toString(),random);
    }

    private String shuffleString(String input, Random random) {
        char[] chars = input.toCharArray();
        for (int i = chars.length-1; i >0 ; i--) {
            int j = random.nextInt(i+1);
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
        }
        return new String(chars);
    }
}
