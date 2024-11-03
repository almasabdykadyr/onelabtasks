package dev.almasabdykadyr.library.service;

import dev.almasabdykadyr.library.dto.AuthRequest;
import dev.almasabdykadyr.library.dto.AuthResponse;
import dev.almasabdykadyr.library.dto.RegisterRequest;
import dev.almasabdykadyr.library.dto.UserRequest;
import dev.almasabdykadyr.library.entity.User;
import dev.almasabdykadyr.library.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {

        var userRequest = UserRequest.builder()
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .firstName(request.firstname())
                .lastName(request.lastname())
                .build();

        User user = userService.addUser(userRequest);

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

        var user = userService.getUserByEmail(request.email());
        var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }
}
