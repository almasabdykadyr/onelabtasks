package dev.almasabdykadyr.library.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

/**
 * DTO for {@link dev.almasabdykadyr.library.entity.User}
 */
public record UserRequest(@Email String email, @NotBlank String password, @NotBlank String firstName,
                          @NotBlank String lastName) implements Serializable {
}