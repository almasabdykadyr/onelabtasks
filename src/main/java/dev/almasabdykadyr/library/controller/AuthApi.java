package dev.almasabdykadyr.library.controller;

import dev.almasabdykadyr.library.dto.AuthRequest;
import dev.almasabdykadyr.library.dto.AuthResponse;
import dev.almasabdykadyr.library.dto.RegisterRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.v3.oas.annotations.Operation;

@RequestMapping("api/v1/auth")
@Tag(name = "Auth API", description = "API for Rental Service")
public interface AuthApi {

    @Operation(
            summary = "Register a new user",
            description = "Endpoint for registering a new user",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Registration successful"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid request")
            }
    )
    @PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
    ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request);

    @Operation(
            summary = "Authenticate user",
            description = "Endpoint for user authentication",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Authentication successful"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized")
            }
    )
    @PostMapping(value = "/authenticate", consumes = "application/json", produces = "application/json")
    ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest request);
}
