package dev.almasabdykadyr.library.entity;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Rental {

    @Id
    @NotNull
    private Long id;
    @NotNull
    private Book book;
    @NotNull
    private User user;
    @NotNull
    private RentStatus status;
    @NotNull
    private LocalDate dueDate;
    @NotNull
    private LocalDateTime createdAt;
}
