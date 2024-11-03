package dev.almasabdykadyr.library.service;

import dev.almasabdykadyr.library.dto.NewRentalRequest;
import dev.almasabdykadyr.library.entity.Book;
import dev.almasabdykadyr.library.entity.RentStatus;
import dev.almasabdykadyr.library.entity.Rental;
import dev.almasabdykadyr.library.entity.User;
import dev.almasabdykadyr.library.repo.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookRentalService {

    private static final int NUMBER_OF_DUE_DAYS = 10;

    private final RentalRepository rentalRepository;

    private final UserService userService;
    private final BookService bookService;

    // Rent a book to a user
    @Transactional(rollbackFor = SQLException.class)
    public Rental rent(NewRentalRequest request) {
        User user = userService.getUserById(request.userId());
        Book book = bookService.getBookById(request.bookId());

        Rental rental = Rental.builder()
                .userId(user.getId())
                .bookId(book.getId())
                .status(RentStatus.RENTED)
                .dueDate(LocalDate.now().plusDays(NUMBER_OF_DUE_DAYS))
                .createdAt(LocalDateTime.now())
                .build();

        return rentalRepository.save(rental);
    }

    @Transactional
    public Rental returnRent(Long rentId) {
        Optional<Rental> optRental = rentalRepository.findById(rentId);
        if (optRental.isEmpty()) throw new IllegalArgumentException("rental with given id not found");

        Rental rental = optRental.get();
        rental.setStatus(RentStatus.RETURNED);

        return rental;
    }

    @Transactional
    public List<Rental> listAllRentals() {
        return rentalRepository.findAll();
    }

    @Transactional
    public List<Rental> findRentalsByUserId(Long userId) {
        return rentalRepository.findAll().stream()
                .filter(rental -> rental.getUserId().equals(userId))
                .toList();
    }

    @Transactional
    public List<Rental> findRentalsByBookId(Long bookId) {
        return rentalRepository.findAll().stream()
                .filter(rental -> rental.getBookId().equals(bookId))
                .toList();
    }
}
