package dev.almasabdykadyr.library.sheduler;

import dev.almasabdykadyr.library.entity.RentStatus;
import dev.almasabdykadyr.library.entity.Rental;
import dev.almasabdykadyr.library.repo.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalScheduler {

    private final RentalRepository repository;

    @Scheduled(cron = "0 0 * * * *")
    public void checkOverdueRentals() {

        List<Rental> overdueRentals = repository.findAll().stream().filter(r -> r.getStatus() == RentStatus.RENTED && r.getDueDate().isBefore(LocalDate.now())).toList();
        overdueRentals = overdueRentals.stream().peek(r -> r.setStatus(RentStatus.OVERDUE)).toList();
        repository.saveAll(overdueRentals);
    }
}
