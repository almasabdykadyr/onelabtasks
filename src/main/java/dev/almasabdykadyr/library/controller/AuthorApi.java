package dev.almasabdykadyr.library.controller;

import dev.almasabdykadyr.library.dto.AuthorRequest;
import dev.almasabdykadyr.library.entity.Author;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("api/v1/authors")
@Tag(name = "Author API", description = "API for Author Service")
public interface AuthorApi {

    @Operation(
            summary = "Add a new author",
            description = "Creates a new author with the provided details.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Author successfully created",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Author.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid input")
            }
    )
    @PostMapping
    ResponseEntity<Author> addAuthor(@RequestBody @Valid AuthorRequest request);

    @Operation(
            summary = "Get all authors",
            description = "Retrieves a list of all authors.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation",
                            content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Author.class))))
            }
    )
    @GetMapping
    ResponseEntity<List<Author>> getAllAuthors();
}
