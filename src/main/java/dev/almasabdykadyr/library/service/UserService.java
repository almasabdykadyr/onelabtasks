package dev.almasabdykadyr.library.service;

import dev.almasabdykadyr.library.dto.UserRequest;
import dev.almasabdykadyr.library.entity.Roles;
import dev.almasabdykadyr.library.entity.User;
import dev.almasabdykadyr.library.exception.UserNotFoundException;
import dev.almasabdykadyr.library.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    @Transactional
    public User addUser(UserRequest request) {
        User user = User.builder()
                .email(request.email())
                .password(request.password())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .roles(Set.of(Roles.USER))
                .createdAt(LocalDateTime.now())
                .build();

        return repository.save(user);
    }

    @Transactional(readOnly = true)
    public List<User> listAllUsers() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public User getUserById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("user not found with given id"));
    }

    @Transactional(readOnly = true)
    public User getUserByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("user not found with given email"));
    }
}
