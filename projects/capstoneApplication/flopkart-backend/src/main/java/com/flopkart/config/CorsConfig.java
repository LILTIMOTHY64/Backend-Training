package com.flopkart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

/**
 * CORS configuration — allows the Angular dev server (localhost:4200)
 * to call the API and send Authorization headers.
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        // Allow Angular dev server origin
        config.setAllowedOrigins(List.of("http://localhost:4200"));

        // Allow all standard HTTP methods
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));

        // Allow headers needed for JWT
        config.setAllowedHeaders(List.of("Authorization", "Content-Type", "Accept"));

        // Expose Authorization header to the browser
        config.setExposedHeaders(List.of("Authorization"));

        // Allow cookies / credentials (needed if you ever use HttpOnly cookie tokens)
        config.setAllowCredentials(true);

        // Cache preflight for 1 hour
        config.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", config);
        return source;
    }
}
