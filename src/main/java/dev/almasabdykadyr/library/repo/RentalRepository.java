package dev.almasabdykadyr.library.repo;

import dev.almasabdykadyr.library.entity.Rental;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepository extends AbstractRepository<Rental, Long> {


    Rental update(Rental rental);
}
