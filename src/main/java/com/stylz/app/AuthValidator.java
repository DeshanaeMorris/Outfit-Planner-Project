package com.stylz.app;


public class AuthValidator {


    // Returns true if email + password are not empty
    public boolean isValidLogin(String email, String password) {
        return email != null && !email.isBlank()
                && password != null && !password.isBlank();
    }
}



