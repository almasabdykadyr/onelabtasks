package dev.almasabdykadyr.library.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

/**
 * DTO for {@link dev.almasabdykadyr.library.entity.Book}
 */
public record BookRequest(@NotBlank String isbn, @NotBlank String title, @NotBlank String description,
                          @NotNull LocalDate publishedAt) {
}