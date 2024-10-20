package dev.almasabdykadyr.library.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table("books")
public class Book {

    @Id
    @NotNull
    private Long id;
    @NotBlank
    private String isbn;
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotNull
    private Long authorId;
    @NotNull
    private LocalDate publishedAt;
    @NotNull
    private LocalDateTime createdAt;
}
