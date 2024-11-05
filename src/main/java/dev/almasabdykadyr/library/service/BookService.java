package dev.almasabdykadyr.library.service;

import dev.almasabdykadyr.library.dto.BookRequest;
import dev.almasabdykadyr.library.entity.Book;
import dev.almasabdykadyr.library.exception.BookNotFoundException;
import dev.almasabdykadyr.library.repo.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository repository;

    @Transactional
    public Book addBook(BookRequest request) {
        Book book = Book.builder()
                .isbn(request.isbn())
                .title(request.title())
                .description(request.description())
                .publishedAt(request.publishedAt())
                .authorId(request.authorId())
                .createdAt(LocalDateTime.now())
                .build();

        return repository.save(book);
    }

    @Transactional
    public List<Book> listAllBooks() {
        return repository.findAll();
    }

    @Transactional
    public Book getBookById(Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("book not found with given id"));
    }
}
