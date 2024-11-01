package dev.almasabdykadyr.library.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("jwt.token")
public record JwtProperties(String secret) {
}