package dev.almasabdykadyr.library.repo;

import dev.almasabdykadyr.library.entity.Rental;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepository extends ListCrudRepository<Rental, Long> {
}
