package dev.almasabdykadyr.library.service;

import dev.almasabdykadyr.library.dto.UserRequest;
import dev.almasabdykadyr.library.entity.User;
import dev.almasabdykadyr.library.exception.UserNotFoundException;
import dev.almasabdykadyr.library.repo.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class UserServiceTest {

    @Mock
    UserRepository repository;

    @InjectMocks
    UserService service;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddUser() {

        UserRequest request = new UserRequest("testuser@example.com", "password", "John", "Doe");

        User user = User.builder().id(52L).email(request.email()).password(request.password()).firstName(request.firstName()).lastName(request.lastName()).createdAt(LocalDateTime.now()).build();

        when(repository.save(any(User.class))).thenReturn(user);

        User actual = service.addUser(request);

        assertNotNull(actual);
        assertEquals(request.firstName(), actual.getFirstName());
        assertEquals(request.lastName(), actual.getLastName());

        verify(repository, times(1)).save(any(User.class));
    }

    @Test
    void testListAllUsers() {
        when(repository.findAll()).thenReturn(List.of(new User(), new User()));

        List<User> users = service.listAllUsers();

        assertEquals(2, users.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testGetUserById_Successful() {

        User user = User.builder().id(1L).build();
        when(repository.findById(1L)).thenReturn(Optional.of(user));

        User actual = service.getUserById(1L);

        assertEquals(user.getId(), actual.getId());
        verify(repository, times(1)).findById(any(Long.class));
    }

    @Test
    void testGetUserByEmail_NotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> service.getUserByEmail("someemail@mail.com"));
        verify(repository, times(1)).findByEmail(any(String.class));
    }

    @Test
    void testGetUserByEmail_Successful() {

        User user = User.builder().email("someemail@mail.com").build();
        when(repository.findByEmail("someemail@mail.com")).thenReturn(Optional.of(user));

        User actual = service.getUserByEmail("someemail@mail.com");

        assertEquals(user.getEmail(), actual.getEmail());
        verify(repository, times(1)).findByEmail(any(String.class));
    }

    @Test
    void testGetUserById_NotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> service.getUserById(1L));
        verify(repository, times(1)).findById(any(Long.class));
    }
}
