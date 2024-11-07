package dev.almasabdykadyr.library.service;

import dev.almasabdykadyr.library.dto.AuthorRequest;
import dev.almasabdykadyr.library.entity.Author;
import dev.almasabdykadyr.library.repo.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;
    @Mock
    private ModelMapper mapper;

    @InjectMocks
    private AuthorService service;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddAuthor() {

        AuthorRequest request = new AuthorRequest("John", "Doe");

        Author author = Author.builder().firstName("John").lastName("Doe").build();

        when(mapper.map(any(AuthorRequest.class), eq(Author.class))).thenReturn(author);
        when(authorRepository.save(any(Author.class))).thenReturn(author);

        Author actual = service.addAuthor(request);

        assertNotNull(actual);
        assertEquals(request.firstName(), actual.getFirstName());
        assertEquals(request.lastName(), actual.getLastName());

        verify(authorRepository, times(1)).save(any(Author.class));
    }

    @Test
    void testListAllAuthors() {
        when(authorRepository.findAll()).thenReturn(List.of(new Author(), new Author()));

        List<Author> authors = service.getAuthors();

        assertEquals(2, authors.size());
        verify(authorRepository, times(1)).findAll();
    }
}
