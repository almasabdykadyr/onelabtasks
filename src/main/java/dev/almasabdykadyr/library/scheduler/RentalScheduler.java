package dev.almasabdykadyr.library.scheduler;

import dev.almasabdykadyr.library.entity.RentStatus;
import dev.almasabdykadyr.library.entity.Rental;
import dev.almasabdykadyr.library.repo.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class RentalScheduler {

    private final RentalRepository rentalRepo;

    @Scheduled(cron = "0 0 * * * *")
    public void checkOverdueRentals() {
        var rentalsIds = rentalRepo.findAllByStatusAndDueDateBefore(RentStatus.RENTED, LocalDate.now())
                .stream()
                .map(Rental::getId)
                .toList();

        rentalRepo.updateByIds(rentalsIds, RentStatus.OVERDUE);
    }
}
