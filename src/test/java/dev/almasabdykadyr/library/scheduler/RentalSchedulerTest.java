package dev.almasabdykadyr.library.scheduler;

import dev.almasabdykadyr.library.entity.RentStatus;
import dev.almasabdykadyr.library.entity.Rental;
import dev.almasabdykadyr.library.repo.RentalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.*;


class RentalSchedulerTest {

    @Mock
    private RentalRepository rentalRepo;

    @InjectMocks
    private RentalScheduler rentalScheduler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void checkOverdueRentals_updatesOverdueRentals() {
        List<Long> overdueRentalIds = List.of(1L, 2L, 3L);
        when(rentalRepo.findAllByStatusAndDueDateBefore(RentStatus.RENTED, LocalDate.now()))
                .thenReturn(List.of(
                        new Rental(1L, 1L, 1L, RentStatus.RENTED, LocalDate.now().minusDays(1), LocalDateTime.now()),
                        new Rental(2L, 1L, 1L, RentStatus.RENTED, LocalDate.now().minusDays(2), LocalDateTime.now()),
                        new Rental(3L, 1L, 1L, RentStatus.RENTED, LocalDate.now().minusDays(3), LocalDateTime.now())
                ));

        rentalScheduler.checkOverdueRentals();

        verify(rentalRepo, times(1)).updateByIds(overdueRentalIds, RentStatus.OVERDUE);
    }
}
