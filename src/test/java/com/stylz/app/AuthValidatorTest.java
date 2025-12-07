package com.stylz.app;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class AuthValidatorTest {


    @Test
    void testValidLogin() {
        AuthValidator validator = new AuthValidator();
        assertTrue(validator.isValidLogin("test@example.com", "1234"));
    }


    @Test
    void testInvalidLogin() {
        AuthValidator validator = new AuthValidator();
        assertFalse(validator.isValidLogin("", ""));
    }
}

