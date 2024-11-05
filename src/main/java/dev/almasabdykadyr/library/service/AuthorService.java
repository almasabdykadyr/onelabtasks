package dev.almasabdykadyr.library.service;

import dev.almasabdykadyr.library.dto.AuthorRequest;
import dev.almasabdykadyr.library.entity.Author;
import dev.almasabdykadyr.library.repo.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final ModelMapper mapper;

    @Transactional
    public Author addAuthor(AuthorRequest request) {
        var author = mapper.map(request, Author.class);

        return authorRepository.save(author);
    }

    @Transactional
    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }
}
