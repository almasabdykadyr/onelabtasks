package dev.almasabdykadyr.library.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

    @Id
    @GeneratedValue
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
