package dev.almasabdykadyr.library.service;

import dev.almasabdykadyr.library.dto.AuthorRequest;
import dev.almasabdykadyr.library.entity.Author;
import dev.almasabdykadyr.library.repo.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Transactional
    public Author addAuthor(AuthorRequest request) {

        var author = Author.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .createdAt(LocalDateTime.now())
                .build();

        return authorRepository.save(author);
    }

    @Transactional
    public List<Author> listAllAuthors() {
        return authorRepository.findAll();
    }
}
