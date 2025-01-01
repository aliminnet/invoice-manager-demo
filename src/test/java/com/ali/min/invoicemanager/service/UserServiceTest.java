package com.ali.min.invoicemanager.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserServiceTest {

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService();
    }

    @Test
    void login_returnsTokenForValidCredentials() {
        String token = userService.login("johndoe@doe.com", "password1");

        assertNotNull(token);
    }

    @Test
    void login_throwsExceptionForInvalidCredentials() {
        assertThrows(RuntimeException.class, () -> userService.login("johndoe@doe.com", "wrongpassword"));
    }

    @Test
    void validateToken_returnsTrueForValidToken() {
        String token = userService.login("johndoe@doe.com", "password1");

        assertTrue(userService.validateToken("Bearer " + token));
    }

    @Test
    void validateToken_returnsFalseForInvalidToken() {
        assertFalse(userService.validateToken("Bearer invalidtoken"));
    }

    @Test
    void validateToken_returnsTrueForHardcodedAdminToken() {
        assertTrue(userService.validateToken("Bearer admin"));
    }

    @Test
    void getUsername_returnsUsernameForValidToken() {
        String token = userService.login("johndoe@doe.com", "password1");

        assertEquals("johndoe@doe.com", userService.getUsername("Bearer " + token));
    }

    @Test
    void getUsername_returnsAdminForHardcodedAdminToken() {
        assertEquals("admin", userService.getUsername("Bearer admin"));
    }

    @Test
    void getUsername_returnsNullForInvalidToken() {
        assertNull(userService.getUsername("Bearer invalidtoken"));
    }
}