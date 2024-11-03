package dev.almasabdykadyr.library.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import dev.almasabdykadyr.library.dto.AuthRequest;
import dev.almasabdykadyr.library.dto.AuthResponse;
import dev.almasabdykadyr.library.dto.RegisterRequest;
import dev.almasabdykadyr.library.dto.UserRequest;
import dev.almasabdykadyr.library.entity.User;
import dev.almasabdykadyr.library.security.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

class AuthServiceTest {

    @Mock
    private UserService userService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthService authService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegister() {
        // Arrange
        RegisterRequest request = new RegisterRequest("test@example.com", "password123", "John", "Doe");

        UserRequest userRequest = UserRequest.builder().email(request.email()).password("encodedPassword").firstName(request.firstname()).lastName(request.lastname()).build();

        User user = new User(); // create a User object with necessary fields
        String jwtToken = "mockJwtToken";

        when(passwordEncoder.encode(request.password())).thenReturn("encodedPassword");
        when(userService.addUser(userRequest)).thenReturn(user);
        when(jwtService.generateToken(user)).thenReturn(jwtToken);

        // Act
        AuthResponse response = authService.register(request);

        // Assert
        assertEquals(jwtToken, response.token());
        verify(passwordEncoder).encode(request.password());
        verify(userService).addUser(userRequest);
        verify(jwtService).generateToken(user);
    }

    @Test
    void testAuthenticate() {
        // Arrange
        AuthRequest request = new AuthRequest("test@example.com", "password123");

        User user = new User(); // create a User object with necessary fields
        String jwtToken = "mockJwtToken";

        when(userService.getUserByEmail(request.email())).thenReturn(user);
        when(jwtService.generateToken(user)).thenReturn(jwtToken);

        // Act
        AuthResponse response = authService.authenticate(request);

        // Assert
        assertEquals(jwtToken, response.token());
        verify(authenticationManager).authenticate(new UsernamePasswordAuthenticationToken(request.email(), request.password()));
        verify(userService).getUserByEmail(request.email());
        verify(jwtService).generateToken(user);
    }
}

