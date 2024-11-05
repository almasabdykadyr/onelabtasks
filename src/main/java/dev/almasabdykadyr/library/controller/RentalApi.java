package dev.almasabdykadyr.library.controller;

import dev.almasabdykadyr.library.dto.NewRentalRequest;
import dev.almasabdykadyr.library.entity.Rental;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/rent")
@Tag(name = "Rental API", description = "API for Rental Service")
public interface RentalApi {

    @Operation(
            summary = "Rent a book",
            description = "Creates a rental record for the specified book and user.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Rental successfully created",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Rental.class))),
                    @ApiResponse(responseCode = "404", description = "Invalid input")
            }
    )
    @PostMapping
    ResponseEntity<Rental> rent(@RequestBody @Valid NewRentalRequest request);

    @Operation(
            summary = "Return a rented book",
            description = "Marks the rental as returned using the rental ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Rental successfully returned",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Rental.class))),
                    @ApiResponse(responseCode = "404", description = "Rental not found")
            }
    )
    @PostMapping("/return/{id}")
    ResponseEntity<Rental> returnRent(@PathVariable("id") Long id);

    @Operation(
            summary = "Get all rentals",
            description = "Retrieves a list of all rentals.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation",
                            content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Rental.class))))
            }
    )
    @GetMapping
    ResponseEntity<List<Rental>> getAllRentals();

    @Operation(
            summary = "Get rentals by book ID",
            description = "Retrieves a list of all rentals for a specific book.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation",
                            content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Rental.class)))),
                    @ApiResponse(responseCode = "404", description = "Book not found")
            }
    )
    @GetMapping("/search")
    ResponseEntity<List<Rental>> getRentBySearch(@RequestParam(required = false) String userId,
                                                       @RequestParam(required = false) String bookId);

    @Operation(
            summary = "Get rentals by user ID",
            description = "Retrieves a list of all rentals for a specific user.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation",
                            content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Rental.class)))),
                    @ApiResponse(responseCode = "404", description = "User not found")
            }
    )
    @GetMapping("/user/{id}")
    ResponseEntity<List<Rental>> getAllRentalsByUserId(@PathVariable Long id);
}

