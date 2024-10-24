package dev.almasabdykadyr.library.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Email
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    @Column(name = "firstname")
    private String firstName;
    @NotBlank
    @Column(name = "lastname")
    private String lastName;
    @NotNull
    private LocalDateTime createdAt;
}
