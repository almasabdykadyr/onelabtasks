package dev.almasabdykadyr.library.service;


import dev.almasabdykadyr.library.dto.NewRentalRequest;
import dev.almasabdykadyr.library.entity.*;
import dev.almasabdykadyr.library.exception.BookNotFoundException;
import dev.almasabdykadyr.library.exception.UserNotFoundException;
import dev.almasabdykadyr.library.repo.RentalRepository;
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
    private UserService userService;
    @Mock
    private BookService bookService;
    @Mock
    private RentalRepository rentalRepository;

    @InjectMocks
    private RentalService service;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRentBook_Success() {
        NewRentalRequest request = new NewRentalRequest(1L, 1L);
        User user = new User();
        Book book = new Book();

        when(userService.getUserById(1L)).thenReturn(user);
        when(bookService.getBookById(1L)).thenReturn(book);
        when(rentalRepository.save(any(Rental.class))).thenReturn(new Rental());

        Rental rental = service.rent(request);

        assertNotNull(rental);
        verify(userService, times(1)).getUserById(1L);
        verify(bookService, times(1)).getBookById(1L);
        verify(rentalRepository, times(1)).save(any(Rental.class));
    }

    @Test
    void testRentBook_UserNotFound() {
        NewRentalRequest request = new NewRentalRequest(99L, 1L);

        when(userService.getUserById(99L)).thenReturn(null);
        when(bookService.getBookById(99L)).thenReturn(new Book());


        assertThrows(UserNotFoundException.class, () -> service.rent(request));
        verify(userService, times(1)).getUserById(99L);
        verify(bookService, times(1)).getBookById(1L);
    }

    @Test
    void testRentBook_BookNotFound() {
        NewRentalRequest request = new NewRentalRequest(1L, 99L);
        when(userService.getUserById(1L)).thenReturn(new User());
        when(bookService.getBookById(99L)).thenReturn(null);

        assertThrows(BookNotFoundException.class, () -> service.rent(request));
        verify(userService, times(1)).getUserById(1L);
        verify(bookService, times(1)).getBookById(99L);
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

        List<Rental> rentals = service.findRentalsByUserIdOrBookId(1L);

        assertEquals(2, rentals.size());
        verify(rentalRepository, times(1)).findAll();
    }
}
