package dev.almasabdykadyr.library.controller;

import dev.almasabdykadyr.library.dto.AuthorRequest;
import dev.almasabdykadyr.library.entity.Author;
import dev.almasabdykadyr.library.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthorController implements AuthorApi {

    private final AuthorService service;

    @Override
    public ResponseEntity<Author> addAuthor(AuthorRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addAuthor(request));
    }

    @Override
    public ResponseEntity<List<Author>> getAllAuthors() {
        return ResponseEntity.ok(service.listAllAuthors());
    }
}
