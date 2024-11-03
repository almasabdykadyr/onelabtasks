package dev.almasabdykadyr.library.service;

import dev.almasabdykadyr.library.dto.BookRequest;
import dev.almasabdykadyr.library.entity.Book;
import dev.almasabdykadyr.library.exception.BookNotFoundException;
import dev.almasabdykadyr.library.repo.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class BookServiceTest {

    @Mock
    private BookRepository repository;

    @InjectMocks
    private BookService service;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddBook() {
        BookRequest request = new BookRequest("123456789", "Test Book", "Description", 1L, LocalDate.now());
        Book book = Book.builder().id(1L).isbn(request.isbn()).title(request.title()).description(request.description()).publishedAt(request.publishedAt()).authorId(request.authorId()).createdAt(LocalDateTime.now()).build();

        when(repository.save(any(Book.class))).thenReturn(book);

        Book savedBook = service.addBook(request);

        assertNotNull(savedBook);
        assertEquals("Test Book", savedBook.getTitle());
        verify(repository, times(1)).save(any(Book.class));
    }

    @Test
    void testListAllBooks() {
        when(repository.findAll()).thenReturn(List.of(new Book(), new Book()));

        List<Book> books = service.listAllBooks();

        assertEquals(2, books.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testGetUserByEmail_Successful() {

        Book book = Book.builder().id(1L).build();
        when(repository.findById(1L)).thenReturn(Optional.of(book));

        Book actual = service.getBookById(1L);

        assertEquals(book.getId(), actual.getId());
        verify(repository, times(1)).findById(any(Long.class));
    }

    @Test
    void testGetUserById_NotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(BookNotFoundException.class, () -> service.getBookById(1L));
        verify(repository, times(1)).findById(any(Long.class));
    }
}
