package dev.almasabdykadyr.library.entity;

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
@Table("RENTALS")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Rental {

    @Id
    @NotNull
    private Long id;
    @NotNull
    private Long bookId;
    @NotNull
    private Long userId;
    @NotNull
    private RentStatus status;
    @NotNull
    private LocalDate dueDate;
    @NotNull
    private LocalDateTime createdAt;
}
