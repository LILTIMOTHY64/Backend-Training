package com.flopkart.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "jwt")
@Data
public class JwtConfig {

    /** Base64-encoded 256-bit secret used to sign tokens. */
    private String secret;

    /** Access token lifetime in milliseconds (default 30 min). */
    private long accessTokenExpiryMs = 1_800_000L;

    /** Refresh token lifetime in milliseconds (default 7 days). */
    private long refreshTokenExpiryMs = 604_800_000L;
}
