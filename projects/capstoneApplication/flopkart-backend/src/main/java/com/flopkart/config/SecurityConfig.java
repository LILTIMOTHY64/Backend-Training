package com.flopkart.config;

import com.flopkart.security.JwtAuthenticationFilter;
import com.flopkart.security.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final UserDetailsServiceImpl userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // Disable CSRF (stateless JWT API)
            .csrf(AbstractHttpConfigurer::disable)
            // CORS handled by CorsConfig bean
            .cors(cors -> {})
            // Stateless session
            .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            // Authorization rules
            .authorizeHttpRequests(auth -> auth
                // ── Public auth endpoints ──────────────────────────────────
                .requestMatchers("/api/auth/login", "/api/auth/refresh").permitAll()
                // ── Public product GET endpoints ───────────────────────────
                .requestMatchers(HttpMethod.GET, "/api/products/**").permitAll()
                // ── Protected: any authenticated user ─────────────────────
                .requestMatchers("/api/auth/logout").authenticated()
                .requestMatchers("/api/cart/**").authenticated()
                // ── Protected: OWNER only (POST/PUT/DELETE products) ───────
                .requestMatchers(HttpMethod.POST,   "/api/products/**").hasRole("OWNER")
                .requestMatchers(HttpMethod.PUT,    "/api/products/**").hasRole("OWNER")
                .requestMatchers(HttpMethod.DELETE, "/api/products/**").hasRole("OWNER")
                // ── Everything else requires auth ──────────────────────────
                .anyRequest().authenticated()
            )
            // Plug in the JWT filter before the default username/password filter
            .authenticationProvider(authenticationProvider())
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
