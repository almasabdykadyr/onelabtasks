package dev.almasabdykadyr.library.controller;

import dev.almasabdykadyr.library.dto.BookRequest;
import dev.almasabdykadyr.library.entity.Book;
import dev.almasabdykadyr.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookController implements BookApi {

    private final BookService service;

    @Override
    public ResponseEntity<Book> addBook(BookRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addBook(request));
    }

    @Override
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(service.listAllBooks());
    }
}
