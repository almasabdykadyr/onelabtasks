package dev.almasabdykadyr.library.service;

import dev.almasabdykadyr.library.dto.AuthorRequest;
import dev.almasabdykadyr.library.dto.BookRequest;
import dev.almasabdykadyr.library.dto.NewRentalRequest;
import dev.almasabdykadyr.library.dto.UserRequest;
import dev.almasabdykadyr.library.entity.*;
import dev.almasabdykadyr.library.exception.BookNotFoundException;
import dev.almasabdykadyr.library.exception.UserNotFoundException;
import dev.almasabdykadyr.library.repo.AuthorRepository;
import dev.almasabdykadyr.library.repo.BookRepository;
import dev.almasabdykadyr.library.repo.RentalRepository;
import dev.almasabdykadyr.library.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookRentalService {

    private static final int NUMBER_OF_DUE_DAYS = 10;

    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final RentalRepository rentalRepository;
    private final AuthorRepository authorRepository;

    public User addUser(UserRequest request) {
        User user = User.builder()
                .email(request.email())
                .password(request.password())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .createdAt(LocalDateTime.now())
                .build();
        return userRepository.save(user);
    }

    public List<User> listAllUsers() {

        return userRepository.findAll();
    }

    public Book addBook(BookRequest request) {
        Book book = Book.builder()
                .isbn(request.isbn())
                .title(request.title())
                .description(request.description())
                .publishedAt(request.publishedAt())
                .authorId(request.authorId())
                .createdAt(LocalDateTime.now())
                .build();
        return bookRepository.save(book);
    }

    public List<Book> listAllBooks() {
        return bookRepository.findAll();
    }

    public Author addAuthor(AuthorRequest request) {

        Author author = Author.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .createdAt(LocalDateTime.now())
                .build();

        return authorRepository.save(author);
    }

    public List<Author> listAllAuthors() {
        return authorRepository.findAll();
    }

    // Rent a book to a user
    public Rental rent(NewRentalRequest request) {
        Optional<User> userOpt = userRepository.findById(request.userId());
        Optional<Book> bookOpt = bookRepository.findById(request.bookId());

        if (userOpt.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }

        if (bookOpt.isEmpty()) {
            throw new BookNotFoundException("Book not found");
        }

        Rental rental = Rental.builder()
                .userId(request.userId())
                .bookId(request.bookId())
                .status(RentStatus.RENTED)
                .dueDate(LocalDate.now().plusDays(NUMBER_OF_DUE_DAYS))
                .createdAt(LocalDateTime.now())
                .build();
        return rentalRepository.save(rental);
    }

    public Rental returnRent(Long rentId) {
        Optional<Rental> optRental = rentalRepository.findById(rentId);
        if (optRental.isEmpty()) throw new IllegalArgumentException("Rental not found");

        Rental rental = optRental.get();
        rental.setStatus(RentStatus.RETURNED);

        return rental;
    }

    public List<Rental> listAllRentals() {
        return rentalRepository.findAll();
    }

    public List<Rental> findRentalsByUserId(Long userId) {
        return rentalRepository.findAll().stream()
                .filter(rental -> rental.getUserId().equals(userId))
                .toList();
    }

    public List<Rental> findRentalsByBookId(Long bookId) {
        return rentalRepository.findAll().stream()
                .filter(rental -> rental.getBookId().equals(bookId))
                .toList();
    }
}
