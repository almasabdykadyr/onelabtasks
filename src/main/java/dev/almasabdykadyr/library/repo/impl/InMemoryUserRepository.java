package dev.almasabdykadyr.library.repo.impl;

import dev.almasabdykadyr.library.entity.User;
import dev.almasabdykadyr.library.repo.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryUserRepository implements UserRepository {

    private final List<User> users = new ArrayList<>();
    private long counter = 0L;

    @Override
    public User save(User user) {

        if (user.getId() == null) {

            user.setId(counter);
            counter += 1;
        }

        users.add(user);

        return user;
    }

    @Override
    public Optional<User> findById(Long id) {

        return users.stream()
                .filter(u -> u.getId() == id)
                .findFirst();
    }

    @Override
    public List<User> findAll() {

        return users;
    }

    @Override
    public void deleteById(Long id) {

        users.remove(findById(id).get());
    }

    @Override
    public boolean existsById(Long id) {

        return users.stream().filter(i -> i.getId().equals(id)).count() == 1;
    }
}
