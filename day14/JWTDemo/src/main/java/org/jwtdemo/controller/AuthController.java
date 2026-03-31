package org.jwtdemo.controller;import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.jwtdemo.dto.LoginRequest;
import org.jwtdemo.dto.LoginResponse;
import org.jwtdemo.security.JwtUtil;
import org.jwtdemo.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private JwtUtil jwtUtil;
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        boolean isValidUser = authService.authenticate(request.getUsername(), request.getPassword());
        if (!isValidUser) {
            return ResponseEntity.status(401).body("Invalid username or password");
        }
        String token = jwtUtil.generateToken(request.getUsername());
        return ResponseEntity.ok(new LoginResponse(token));
    }
}