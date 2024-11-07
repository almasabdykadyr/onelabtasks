package dev.almasabdykadyr.library.service;


import dev.almasabdykadyr.library.dto.NewRentalRequest;
import dev.almasabdykadyr.library.entity.Book;
import dev.almasabdykadyr.library.entity.RentStatus;
import dev.almasabdykadyr.library.entity.Rental;
import dev.almasabdykadyr.library.exception.BookNotFoundException;
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

class RentalServiceTest {


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
        Book book = new Book();

        when(bookService.getBookById(1L)).thenReturn(book);
        when(rentalRepository.save(any(Rental.class))).thenReturn(new Rental());

        Rental rental = service.rent(request);

        assertNotNull(rental);
        verify(bookService, times(1)).getBookById(1L);
        verify(rentalRepository, times(1)).save(any(Rental.class));
    }

    @Test
    void testRentBook_BookNotFound() {
        NewRentalRequest request = new NewRentalRequest(1L, 99L);
        when(bookService.getBookById(99L)).thenThrow(BookNotFoundException.class);

        assertThrows(BookNotFoundException.class, () -> service.rent(request));
        verify(bookService, times(1)).getBookById(99L);
    }

    @Test
    void returnRent_ShouldUpdateStatusToReturned_WhenRentalExists() {
        Long rentId = 1L;
        Rental rental = new Rental();
        rental.setStatus(RentStatus.RENTED);

        when(rentalRepository.findById(rentId)).thenReturn(Optional.of(rental));

        Rental returnedRental = service.returnRent(rentId);

        verify(rentalRepository).updateByIdAndStatus(RentStatus.RETURNED, rentId);
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
}
