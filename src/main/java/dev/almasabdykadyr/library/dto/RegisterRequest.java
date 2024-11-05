package dev.almasabdykadyr.library.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record RegisterRequest(@Email String email,
                              @NotBlank String password,
                              @NotBlank String firstname,
                              @NotBlank String lastname) {}
