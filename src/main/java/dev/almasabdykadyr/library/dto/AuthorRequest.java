package dev.almasabdykadyr.library.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * DTO for {@link dev.almasabdykadyr.library.entity.Author}
 */
public record AuthorRequest(@NotBlank String firstName,
                            @NotBlank String lastName) {
}