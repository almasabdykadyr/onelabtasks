package dev.almasabdykadyr.library.controller;

import dev.almasabdykadyr.library.dto.AuthRequest;
import dev.almasabdykadyr.library.dto.AuthResponse;
import dev.almasabdykadyr.library.dto.RegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("api/v1/auth")
public interface AuthApi {

    @PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
    ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request);

    @PostMapping(value = "/authenticate", consumes = "application/json", produces = "application/json")
    ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest request);
}
