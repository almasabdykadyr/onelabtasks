package dev.almasabdykadyr.library.service;

import dev.almasabdykadyr.library.dto.BookRequest;
import dev.almasabdykadyr.library.entity.Book;
import dev.almasabdykadyr.library.repo.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

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

        when(bookRepository.save(any(Book.class))).thenReturn(book);

        Book savedBook = service.addBook(request);

        assertNotNull(savedBook);
        assertEquals("Test Book", savedBook.getTitle());
        verify(bookRepository, times(1)).save(any(Book.class));
    }

    @Test
    void testListAllBooks() {
        when(bookRepository.findAll()).thenReturn(List.of(new Book(), new Book()));

        List<Book> books = service.listAllBooks();

        assertEquals(2, books.size());
        verify(bookRepository, times(1)).findAll();
    }

}
