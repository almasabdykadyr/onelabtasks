package dev.almasabdykadyr.library.repo.impl;

import dev.almasabdykadyr.library.entity.Author;
import dev.almasabdykadyr.library.repo.AuthorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryAuthorRepository implements AuthorRepository {

    private final List<Author> authors = new ArrayList<>();
    private long counter = 0L;

    @Override
    public Author save(Author author) {

        if (author.getId() == null) {

            author.setId(counter);
            counter += 1;
        }

        authors.add(author);

        return author;
    }

    @Override
    public Optional<Author> findById(Long id) {

        return authors.stream()
                .filter(u -> u.getId() == id)
                .findFirst();
    }

    @Override
    public List<Author> findAll() {
        return authors;
    }

    @Override
    public void deleteById(Long id) {

        authors.remove(findById(id).get());
    }

    @Override
    public boolean existsById(Long id) {
        return authors.stream().filter(i -> i.getId().equals(id)).count() == 1;
    }
}
