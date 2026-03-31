package com.flopkart.service;

import com.flopkart.dto.LoginRequest;
import com.flopkart.dto.LoginResponse;
import com.flopkart.dto.RefreshTokenRequest;
import com.flopkart.dto.RefreshTokenResponse;
import com.flopkart.exception.ResourceNotFoundException;
import com.flopkart.model.User;
import com.flopkart.repository.UserRepository;
import com.flopkart.security.JwtUtil;
import com.flopkart.security.UserDetailsServiceImpl;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    /**
     * Authenticates the user and returns a full login response containing
     * access and refresh JWT tokens.
     */
    public LoginResponse login(LoginRequest request) {
        // Validate credentials — throws BadCredentialsException on failure
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", request.getUsername()));

        String accessToken = jwtUtil.generateAccessToken(userDetails);
        String refreshToken = jwtUtil.generateRefreshToken(userDetails);

        return LoginResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .role(user.getRole().name())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    /**
     * Issues a new access + refresh token pair from a valid refresh token.
     */
    public RefreshTokenResponse refresh(RefreshTokenRequest request) {
        String token = request.getRefreshToken();
        try {
            if (!jwtUtil.isRefreshToken(token)) {
                throw new JwtException("Not a refresh token");
            }
            String username = jwtUtil.extractUsername(token);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (!jwtUtil.isTokenValid(token, userDetails)) {
                throw new JwtException("Token expired or invalid");
            }

            return RefreshTokenResponse.builder()
                    .accessToken(jwtUtil.generateAccessToken(userDetails))
                    .refreshToken(jwtUtil.generateRefreshToken(userDetails))
                    .build();

        } catch (JwtException | AuthenticationException e) {
            throw new org.springframework.security.authentication.BadCredentialsException(
                    "Invalid or expired refresh token");
        }
    }

    /**
     * Logout is stateless — the client simply discards its tokens.
     * For a production implementation, the refresh token would be added to a
     * deny-list here (e.g. stored in Redis).
     */
    public void logout(String username) {
        // Stateless — no server-side token invalidation in this implementation.
        // A deny-list (Redis / DB) would be added here for production use.
    }
}
