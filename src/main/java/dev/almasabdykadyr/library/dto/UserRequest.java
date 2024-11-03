package dev.almasabdykadyr.library.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.io.Serializable;

/**
 * DTO for {@link dev.almasabdykadyr.library.entity.User}
 */
@Builder
public record UserRequest(@Email String email, @NotBlank String password, @NotBlank String firstName,
                          @NotBlank String lastName) implements Serializable {
}