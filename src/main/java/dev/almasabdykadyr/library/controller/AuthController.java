package dev.almasabdykadyr.library.controller;

import dev.almasabdykadyr.library.dto.AuthRequest;
import dev.almasabdykadyr.library.dto.AuthResponse;
import dev.almasabdykadyr.library.dto.RegisterRequest;
import dev.almasabdykadyr.library.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController implements AuthApi {

    private final AuthService authService;

    @Override
    public ResponseEntity<AuthResponse> register(RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @Override
    public ResponseEntity<AuthResponse> authenticate(AuthRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }
}
