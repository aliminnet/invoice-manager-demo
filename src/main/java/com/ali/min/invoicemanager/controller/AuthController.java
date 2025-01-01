package com.ali.min.invoicemanager.controller;

import com.ali.min.invoicemanager.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/login")
    @Operation(summary = "Login and get the token")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");
        String token = userService.login(email, password);
        return ResponseEntity.ok(Map.of("token", token));
    }

    @GetMapping("/validate")
    @Operation(summary = "Validate the token")
    public ResponseEntity<Map<String, Boolean>> validate(@RequestHeader("Authorization") String token) {
        boolean isValid = userService.validateToken(token);
        return ResponseEntity.ok(Map.of("valid", isValid));
    }
}


