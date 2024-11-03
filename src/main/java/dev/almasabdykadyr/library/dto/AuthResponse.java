package dev.almasabdykadyr.library.dto;

import lombok.Builder;

@Builder
public record AuthResponse(String token) {
}
