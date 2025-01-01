package com.ali.min.invoicemanager.service;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 *  Service class for user operations
 *
 *  @version 1.0
 */
@Service
public class UserService {
    private final Map<String, String> userStore = new HashMap<>(); // email -> password
    private final Map<String, String> tokenStore = new HashMap<>(); // token -> email


    public UserService() {
        userStore.put("johndoe@doe.com", "password1");
        userStore.put("janedoe@doe.com", "password2");
    }

    /**
     * Login operation and return token
     *
     * @param email User email
     * @param password User password
     * @return Token
     */
    public String login(String email, String password) {
        if (userStore.containsKey(email) && userStore.get(email).equals(password)) {
            String token = UUID.randomUUID().toString();
            tokenStore.put("Bearer " + token, email);
            return token;
        }
        throw new RuntimeException("Invalid username or password");
    }

    /**
     * Check if token is valid
     *
     * @param token Token
     * @return Boolean is token valid
     */
    public boolean validateToken(String token) {
        if (token.equals("Bearer admin")) { //Demo amacli hardcoded admin token
            return true;
        }
        return tokenStore.containsKey(token);
    }

    /**
     * Get username of the token
     *
     * @param token Token
     * @return username of the token
     */
    public String getUsername(String token) {
        if (token.equals("Bearer admin")) {  //Demo amacli hardcoded admin token
            return "admin";
        }
        return tokenStore.get(token);
    }
}


