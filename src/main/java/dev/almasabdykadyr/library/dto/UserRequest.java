package dev.almasabdykadyr.library.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link dev.almasabdykadyr.library.entity.User}
 */
public record UserRequest(@NotNull Long id, @Email String email, @NotBlank String password, @NotBlank String firstName,
                          @NotBlank String lastName, @NotNull LocalDateTime createdAt) implements Serializable {
}