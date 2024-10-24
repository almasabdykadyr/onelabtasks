package dev.almasabdykadyr.library.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "rentals")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rental {

    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private Long bookId;
    @NotNull
    private Long userId;
    @Enumerated(EnumType.STRING)
    private RentStatus status;
    @NotNull
    private LocalDate dueDate;
    @NotNull
    private LocalDateTime createdAt;
}
