package com.example.pathfindr.service.validationService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class ValidationServicImplt implements ValidationService {

    private String usernamePattern = "^(?![0-9 ])[a-zA-Z0-9_-]{3,20}$";

    private String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$";

    private String emailPattern = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
            + "A-Z]{2,7}$";

    @Override
    public void validateToken(String token) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'validateToken'");
    }

    @Override
    public void validatePassword(String password) {
        Matcher matcher = Pattern.compile(passwordPattern).matcher(password);

        if (password == "") {

            throw new IllegalArgumentException(
                    "password required");
        }

        if (!matcher.matches()) {
            throw new IllegalArgumentException(
                    "Invalid password!!! Password must contain at least one digit, one lowercase, one uppercase, one special character and length between 8 to 20");
        }

    }

    @Override
    public void validateEmail(String email) {
        Matcher matcher = Pattern.compile(emailPattern).matcher(email);

        if (email == "") {

            throw new IllegalArgumentException(
                    "email required");

        }


        if (!matcher.matches()) {
            throw new IllegalArgumentException(
                    "Invalid email!!! Email must be in the format of:example@email.com");
        }

    }

    @Override
    public void validateUsername(String username) {
        if (username == "") {

            throw new IllegalArgumentException(
                    "username required");

        }
        Matcher matcher = Pattern.compile(usernamePattern).matcher(username);

        if (!matcher.matches()) {

            throw new IllegalArgumentException(
                    "Invalid username!!!");

        }
    }

    // @Override
    // public void checkIfUserExists(User user) {
    //     // check if email already exists
    //     try {

    //         if (userRepository.findByEmail(user.getEmail()) != null) {
    //             throw new IllegalArgumentException(
    //                     "Email already exists!!!");
    //         }

    //     } catch (Exception e) {

    //         e.getMessage();
    //     }
    // }


}
