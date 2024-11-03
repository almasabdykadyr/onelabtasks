package dev.almasabdykadyr.library.service;

import dev.almasabdykadyr.library.dto.AuthRequest;
import dev.almasabdykadyr.library.dto.AuthResponse;
import dev.almasabdykadyr.library.dto.RegisterRequest;
import dev.almasabdykadyr.library.entity.Roles;
import dev.almasabdykadyr.library.entity.User;
import dev.almasabdykadyr.library.repo.UserRepository;
import dev.almasabdykadyr.library.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .firstName(request.firstname())
                .lastName(request.lastname())
                .roles(Set.of(Roles.USER))
                .createdAt(LocalDateTime.now())
                .build();

        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );
        var user = repository.findByEmail(request.email())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }
}
