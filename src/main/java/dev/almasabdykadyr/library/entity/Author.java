package dev.almasabdykadyr.library.entity;

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
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table("AUTHORS")
public class Author {

    @Id
    private Long id;
    @NotBlank
    @Column("FIRSTNAME")
    private String firstName;
    @NotBlank
    @Column("LASTNAME")
    private String lastName;
    @NotNull
    @Column("CREATED_AT")
    private LocalDateTime createdAt;
}
