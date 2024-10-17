package dev.almasabdykadyr.library.repo.impl;

import dev.almasabdykadyr.library.entity.Book;
import dev.almasabdykadyr.library.repo.BookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryBookRepository implements BookRepository {

    private final List<Book> books = new ArrayList<>();
    private long counter = 0L;

    @Override
    public Book save(Book book) {

        if (book.getId() == null) {

            book.setId(counter);
            counter += 1;
        }

        books.add(book);

        return book;
    }

    @Override
    public Optional<Book> findById(Long id) {

        return books.stream()
                .filter(u -> u.getId() == id)
                .findFirst();
    }

    @Override
    public List<Book> findAll() {
        return books;
    }

    @Override
    public void deleteById(Long id) {
        books.remove(findById(id).get());
    }

    @Override
    public boolean existsById(Long id) {

        return books.stream().filter(i -> i.getId().equals(id)).count() == 1;
    }
}
