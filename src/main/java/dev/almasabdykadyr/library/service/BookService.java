package dev.almasabdykadyr.library.service;

import dev.almasabdykadyr.library.dto.BookRequest;
import dev.almasabdykadyr.library.entity.Book;
import dev.almasabdykadyr.library.exception.BookNotFoundException;
import dev.almasabdykadyr.library.repo.BookRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository repository;
    private final ModelMapper mapper;

    @Transactional
    public Book addBook(BookRequest request) {

        var book = mapper.map(request, Book.class);

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
