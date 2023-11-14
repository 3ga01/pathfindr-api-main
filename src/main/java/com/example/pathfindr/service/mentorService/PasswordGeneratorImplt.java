package com.example.pathfindr.service.mentorService;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class PasswordGeneratorImplt implements PasswordGenerator {

    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$";
    private static final String LOWERCASE_CHARS = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARS = "@#$%^&+=";

    private static final int MIN_PASSWORD_LENGTH = 8;
    private static final int MAX_PASSWORD_LENGTH = 20;

    public String generatePassword() {
        Random random = new Random();
        StringBuilder passwordBuilder = new StringBuilder();

        appendRandomChar(passwordBuilder, LOWERCASE_CHARS, random);
        appendRandomChar(passwordBuilder, UPPERCASE_CHARS, random);
        appendRandomChar(passwordBuilder, DIGITS, random);
        appendRandomChar(passwordBuilder, SPECIAL_CHARS, random);

        for (int i = MIN_PASSWORD_LENGTH; i < MAX_PASSWORD_LENGTH; i++) {
            String charSet = LOWERCASE_CHARS + UPPERCASE_CHARS + DIGITS + SPECIAL_CHARS;
            appendRandomChar(passwordBuilder, charSet, random);
        }

        String generatedPassword = passwordBuilder.toString();

        if (isValidPassword(generatedPassword)) {
            return generatedPassword;
        } else {
            return generatePassword();
        }
    }

    private static void appendRandomChar(StringBuilder stringBuilder, String charSet, Random random) {
        stringBuilder.append(charSet.charAt(random.nextInt(charSet.length())));
    }

    public static boolean isValidPassword(String password) {
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

}
