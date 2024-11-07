package dev.almasabdykadyr.library.service;

import dev.almasabdykadyr.library.dto.NewRentalRequest;
import dev.almasabdykadyr.library.entity.Book;
import dev.almasabdykadyr.library.entity.RentStatus;
import dev.almasabdykadyr.library.entity.Rental;
import dev.almasabdykadyr.library.repo.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalService {

    private static final int NUMBER_OF_DUE_DAYS = 10;

    private final RentalRepository rentalRepository;
    private final BookService bookService;

    @Transactional
    public Rental rent(NewRentalRequest request) {
        Book book = bookService.getBookById(request.bookId());

        Rental rental = Rental.builder()
                .userId(request.userId())
                .bookId(book.getId())
                .status(RentStatus.RENTED)
                .dueDate(LocalDate.now().plusDays(NUMBER_OF_DUE_DAYS))
                .createdAt(LocalDateTime.now())
                .build();

        return rentalRepository.save(rental);
    }

    @Transactional
    public Rental returnRent(Long rentId) {
        return rentalRepository.updateByIdAndStatus(RentStatus.RETURNED, rentId);
    }

    @Transactional(readOnly = true)
    public List<Rental> listAllRentals() {
        return rentalRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Rental> findRentalsByUserIdOrBookId(Long userId, Long bookId) {
        return rentalRepository.findByUserIdOrBookId(userId, bookId);
    }

}
