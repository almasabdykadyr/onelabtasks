package dev.almasabdykadyr.library.service;


import dev.almasabdykadyr.library.dto.BookRequest;
import dev.almasabdykadyr.library.dto.NewRentalRequest;
import dev.almasabdykadyr.library.dto.UserRequest;
import dev.almasabdykadyr.library.entity.Book;
import dev.almasabdykadyr.library.entity.Rental;
import dev.almasabdykadyr.library.entity.User;
import dev.almasabdykadyr.library.exception.BookNotFoundException;
import dev.almasabdykadyr.library.exception.UserNotFoundException;
import dev.almasabdykadyr.library.repo.AuthorRepository;
import dev.almasabdykadyr.library.repo.BookRepository;
import dev.almasabdykadyr.library.repo.RentalRepository;
import dev.almasabdykadyr.library.repo.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BookRentalServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private RentalRepository rentalRepository;
    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private BookRentalService service;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddUser() {

        UserRequest request = new UserRequest("testuser@example.com", "password", "John", "Doe");

        User user = User.builder().id(52L).email(request.email()).password(request.password()).firstName(request.firstName()).lastName(request.lastName()).createdAt(LocalDateTime.now()).build();

        when(userRepository.save(any(User.class))).thenReturn(user);

        User actual = service.addUser(request);

        assertNotNull(actual);
        assertEquals(request.firstName(), actual.getFirstName());
        assertEquals(request.lastName(), actual.getLastName());

        verify(userRepository, times(1)).save(any(User.class));
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
    void testRentBook_Success() {
        NewRentalRequest request = new NewRentalRequest(1L, 1L);
        User user = new User();
        Book book = new Book();

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(rentalRepository.save(any(Rental.class))).thenReturn(new Rental());

        Rental rental = service.rent(request);

        assertNotNull(rental);
        verify(userRepository, times(1)).findById(1L);
        verify(bookRepository, times(1)).findById(1L);
        verify(rentalRepository, times(1)).save(any(Rental.class));
    }

    @Test
    void testRentBook_UserNotFound() {
        NewRentalRequest request = new NewRentalRequest(99L, 1L);

        when(userRepository.findById(99L)).thenReturn(Optional.empty());
        when(bookRepository.findById(99L)).thenReturn(Optional.of(new Book()));


        assertThrows(UserNotFoundException.class, () -> service.rent(request));
        verify(userRepository, times(1)).findById(99L);
        verify(bookRepository, times(1)).findById(1L);
    }

    @Test
    void testRentBook_BookNotFound() {
        NewRentalRequest request = new NewRentalRequest(1L, 99L);
        when(userRepository.findById(1L)).thenReturn(Optional.of(new User()));
        when(bookRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(BookNotFoundException.class, () -> service.rent(request));
        verify(userRepository, times(1)).findById(1L);
        verify(bookRepository, times(1)).findById(99L);
    }
}
