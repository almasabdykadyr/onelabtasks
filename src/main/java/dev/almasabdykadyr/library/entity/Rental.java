package dev.almasabdykadyr.library.entity;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Rental {

    @NotNull
    private Long id;
    @NotNull
    private Book book;
    @NotNull
    private User user;
    @Enumerated(EnumType.STRING)
    private RentStatus status;
    @NotNull
    private LocalDate dueDate;
    @NotNull
    private LocalDateTime createdAt;
}
