package dev.almasabdykadyr.library.service;


import dev.almasabdykadyr.library.dto.AuthorRequest;
import dev.almasabdykadyr.library.dto.NewRentalRequest;
import dev.almasabdykadyr.library.entity.*;
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

import java.util.List;
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
    @Mock
    private NotificationService notificationService;


    @InjectMocks
    private BookRentalService service;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddAuthor() {

        AuthorRequest request = new AuthorRequest("John", "Doe");

        Author author = Author.builder().firstName("John").lastName("Doe").build();

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

        List<Author> authors = service.listAllAuthors();

        assertEquals(2, authors.size());
        verify(authorRepository, times(1)).findAll();
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

    @Test
    void returnRent_ShouldUpdateStatusToReturned_WhenRentalExists() {
        Long rentId = 1L;
        Rental rental = new Rental();
        rental.setStatus(RentStatus.RENTED);

        when(rentalRepository.findById(rentId)).thenReturn(Optional.of(rental));

        Rental returnedRental = service.returnRent(rentId);

        assertEquals(RentStatus.RETURNED, returnedRental.getStatus());
        verify(rentalRepository).findById(rentId); // Ensure repository was called
    }

    @Test
    void returnRent_ShouldThrowException_WhenRentalNotFound() {
        // Arrange
        Long rentId = 2L;
        when(rentalRepository.findById(rentId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(IllegalArgumentException.class,
                () -> service.returnRent(rentId),
                "Rental not found");
        verify(rentalRepository).findById(rentId); // Ensure repository was called
    }

    @Test
    void testListAllRentals() {
        when(rentalRepository.findAll()).thenReturn(List.of(new Rental(), new Rental()));

        List<Rental> rentals = service.listAllRentals();

        assertEquals(2, rentals.size());
        verify(rentalRepository, times(1)).findAll();
    }

    @Test
    void testFindRentalsByUserId() {
        Rental rental1 = new Rental();
        rental1.setUserId(1L);
        Rental rental2 = new Rental();
        rental2.setUserId(1L);

        when(rentalRepository.findAll()).thenReturn(List.of(rental1, rental2));

        List<Rental> rentals = service.findRentalsByUserId(1L);

        assertEquals(2, rentals.size());
        verify(rentalRepository, times(1)).findAll();
    }

    @Test
    void testFindRentalsByBookId() {
        Rental rental1 = new Rental();
        rental1.setBookId(1L);
        Rental rental2 = new Rental();
        rental2.setBookId(1L);

        when(rentalRepository.findAll()).thenReturn(List.of(rental1, rental2));

        List<Rental> rentals = service.findRentalsByBookId(1L);

        assertEquals(2, rentals.size());
        verify(rentalRepository, times(1)).findAll();
    }
}
