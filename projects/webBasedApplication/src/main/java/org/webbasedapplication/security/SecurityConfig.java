package org.webbasedapplication.security;

import tools.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Map;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsManager(PasswordEncoder encoder) {
        UserDetails admin = User.builder()
                .username("admin")
                .password(encoder.encode("admin123"))
                .roles("ADMIN")
                .build();

        UserDetails user = User.builder()
                .username("user")
                .password(encoder.encode("user123"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        http
            // Disable CSRF – stateless REST API
            .csrf(csrf -> csrf.disable())

            .authorizeHttpRequests(auth -> auth
                // Serve the static web UI without authentication
                .requestMatchers("/", "/index.html", "/favicon.ico").permitAll()
                // Anyone with USER or ADMIN can read employees
                .requestMatchers(HttpMethod.GET,    "/employees", "/employees/**").hasAnyRole("USER", "ADMIN")
                // Only ADMIN can create, update, delete
                .requestMatchers(HttpMethod.POST,   "/employees").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT,    "/raise").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/employees/**").hasRole("ADMIN")
                // All other requests must be authenticated
                .anyRequest().authenticated()
            )

            // 401 – no credentials / wrong credentials
            .exceptionHandling(ex -> ex
                .authenticationEntryPoint((request, response, authException) -> {
                    response.setStatus(401);
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    mapper.writeValue(response.getWriter(),
                            Map.of("error", "Unauthorized: " + authException.getMessage()));
                })
                // 403 – authenticated but lacks the required role
                .accessDeniedHandler((request, response, accessDeniedException) -> {
                    response.setStatus(403);
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    mapper.writeValue(response.getWriter(),
                            Map.of("error", "Forbidden: You do not have permission to perform this action."));
                })
            )

            // HTTP Basic authentication
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
