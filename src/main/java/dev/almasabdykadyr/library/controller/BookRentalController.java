package dev.almasabdykadyr.library.controller;

import dev.almasabdykadyr.library.dto.BookRequest;
import dev.almasabdykadyr.library.dto.NewRentalRequest;
import dev.almasabdykadyr.library.dto.UserRequest;
import dev.almasabdykadyr.library.entity.Book;
import dev.almasabdykadyr.library.entity.Rental;
import dev.almasabdykadyr.library.entity.User;
import dev.almasabdykadyr.library.service.BookRentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rentals")
@RequiredArgsConstructor
public class BookRentalController {

    private final BookRentalService bookRentalService;

    // Add a new user
    @PostMapping("/users")
    public ResponseEntity<User> addUser(@RequestBody UserRequest request) {
        User user = bookRentalService.addUser(request);
        return ResponseEntity.ok(user);
    }

    // Add a new book
    @PostMapping("/books")
    public ResponseEntity<Book> addBook(@RequestBody BookRequest request) {
        Book book = bookRentalService.addBook(request);
        return ResponseEntity.ok(book);
    }

    // Rent a book
    @PostMapping("/rent")
    public ResponseEntity<Rental> rentBook(@RequestBody NewRentalRequest request) {
        Rental rental = bookRentalService.rentBook(request);
        return ResponseEntity.ok(rental);
    }

    // Return a book
    @PostMapping("/return/{rentId}")
    public ResponseEntity<Rental> returnBook(@PathVariable Long rentId) {
        Rental rental = bookRentalService.returnBook(rentId);
        return ResponseEntity.ok(rental);
    }

    // Get all rentals
    @GetMapping
    public ResponseEntity<List<Rental>> getAllRentals() {
        List<Rental> rentals = bookRentalService.listAllRentals();
        return ResponseEntity.ok(rentals);
    }

    // Get rentals by user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Rental>> getRentalsByUser(@PathVariable Long userId) {
        List<Rental> rentals = bookRentalService.findRentalsByUser(userId);
        return ResponseEntity.ok(rentals);
    }

    // Get rentals by book
    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<Rental>> getRentalsByBook(@PathVariable Long bookId) {
        List<Rental> rentals = bookRentalService.findRentalsByBook(bookId);
        return ResponseEntity.ok(rentals);
    }
}