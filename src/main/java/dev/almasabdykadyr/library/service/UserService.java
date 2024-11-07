package dev.almasabdykadyr.library.service;

import dev.almasabdykadyr.library.dto.UserRequest;
import dev.almasabdykadyr.library.entity.User;
import dev.almasabdykadyr.library.exception.UserNotFoundException;
import dev.almasabdykadyr.library.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final ModelMapper mapper;

    @Transactional
    public User addUser(UserRequest request) {
        var user = mapper.map(request, User.class);

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
