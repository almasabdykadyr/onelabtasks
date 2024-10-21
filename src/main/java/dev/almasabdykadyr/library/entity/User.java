package dev.almasabdykadyr.library.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table("USERS")
public class User {

    @Id
    @NotNull
    private Long id;
    @Email
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    @Column("FIRSTNAME")
    private String firstName;
    @NotBlank
    @Column("LASTNAME")
    private String lastName;
    @NotNull
    private LocalDateTime createdAt;
}
