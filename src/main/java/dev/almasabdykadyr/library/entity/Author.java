package dev.almasabdykadyr.library.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "authors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Author {

    @Id
    @GeneratedValue
    private Long id;
    @NotBlank
    @Column(name = "firstname")
    private String firstName;
    @NotBlank
    @Column(name = "lastname")
    private String lastName;
    @NotNull
    private LocalDateTime createdAt;
}
