package dev.almasabdykadyr.library.controller;

import dev.almasabdykadyr.library.dto.AuthorRequest;
import dev.almasabdykadyr.library.dto.NewRentalRequest;
import dev.almasabdykadyr.library.entity.Author;
import dev.almasabdykadyr.library.entity.Rental;
import dev.almasabdykadyr.library.service.BookRentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class RentalController implements RentalApi {

    private final BookRentalService service;

    @Override
    public ResponseEntity<Author> addAuthor(AuthorRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addAuthor(request));
    }

    @Override
    public ResponseEntity<List<Author>> getAllAuthors() {
        return ResponseEntity.ok(service.listAllAuthors());
    }


    @Override
    public ResponseEntity<Rental> rent(NewRentalRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.rent(request));
    }

    @Override
    public ResponseEntity<Rental> returnRent(Long id) {
        return ResponseEntity.ok(service.returnRent(id));
    }

    @Override
    public ResponseEntity<List<Rental>> getAllRentals() {
        return ResponseEntity.ok(service.listAllRentals());
    }

    @Override
    public ResponseEntity<List<Rental>> getAllRentalsByBookId(Long id) {
        return ResponseEntity.ok(service.findRentalsByBookId(id));
    }

    @Override
    public ResponseEntity<List<Rental>> getAllRentalsByUserId(Long id) {
        return ResponseEntity.ok(service.findRentalsByUserId(id));
    }
}
