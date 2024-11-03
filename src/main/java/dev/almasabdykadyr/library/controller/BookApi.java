package dev.almasabdykadyr.library.controller;

import dev.almasabdykadyr.library.dto.BookRequest;
import dev.almasabdykadyr.library.entity.Book;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("api/v1/")
public interface BookApi {

    @Operation(
            summary = "Add a new book",
            description = "Creates a new book with the provided details.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Book successfully created",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Book.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid input")
            }
    )
    @PostMapping("/books")
    ResponseEntity<Book> addBook(@RequestBody @Valid BookRequest request);

    @Operation(
            summary = "Get all books",
            description = "Retrieves a list of all books.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation",
                            content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Book.class))))
            }
    )
    @GetMapping("/books")
    ResponseEntity<List<Book>> getAllBooks();

}
