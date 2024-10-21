package dev.almasabdykadyr.library.controller;

import dev.almasabdykadyr.library.dto.AuthorRequest;
import dev.almasabdykadyr.library.dto.BookRequest;
import dev.almasabdykadyr.library.dto.NewRentalRequest;
import dev.almasabdykadyr.library.dto.UserRequest;
import dev.almasabdykadyr.library.entity.Author;
import dev.almasabdykadyr.library.entity.Book;
import dev.almasabdykadyr.library.entity.Rental;
import dev.almasabdykadyr.library.entity.User;
import dev.almasabdykadyr.library.service.BookRentalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class RentalController {

    private final BookRentalService service;

    @PostMapping("/users")
    public ResponseEntity<User> addUser(@RequestBody @Valid UserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addUser(request));
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(service.listAllUsers());
    }

    @PostMapping("/authors")
    public ResponseEntity<Author> addAuthor(@RequestBody @Valid AuthorRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addAuthor(request));
    }

    @GetMapping("/authors")
    public ResponseEntity<List<Author>> getAllAuthors() {
        return ResponseEntity.ok(service.listAllAuthors());
    }

    @PostMapping("/books")
    public ResponseEntity<Book> addBook(@RequestBody @Valid BookRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addBook(request));
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(service.listAllBooks());
    }

    @PostMapping("/rent")
    public ResponseEntity<Rental> rent(@RequestBody @Valid NewRentalRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.rent(request));
    }

    @GetMapping("/rent/return/{id}")
    public ResponseEntity<Rental> returnRent(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.returnRent(id));
    }

    @GetMapping("/rent")
    public ResponseEntity<List<Rental>> getAllRents() {
        return ResponseEntity.ok(service.listAllRentals());
    }

    @GetMapping("/rent/book/{id}")
    public ResponseEntity<List<Rental>> getAllRentsByBookId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.findRentalsByBookId(id));
    }

    @GetMapping("/rent/user/{id}")
    public ResponseEntity<List<Rental>> getAllRentsByUserId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.findRentalsByUserId(id));
    }
}
