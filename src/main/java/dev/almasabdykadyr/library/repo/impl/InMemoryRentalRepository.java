package dev.almasabdykadyr.library.repo.impl;

import dev.almasabdykadyr.library.entity.Rental;
import dev.almasabdykadyr.library.repo.RentalRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryRentalRepository implements RentalRepository {

    private final List<Rental> rentals = new ArrayList<>();
    private long counter = 0L;

    @Override
    public Rental save(Rental rental) {

        if (rental.getId() == null) {

            rental.setId(counter);
            counter += 1;
        }

        rentals.add(rental);

        return rental;
    }

    @Override
    public Optional<Rental> findById(Long id) {

        return rentals.stream()
                .filter(r -> r.getId() == id)
                .findFirst();
    }

    @Override
    public List<Rental> findAll() {
        return rentals;
    }

    @Override
    public Rental update(Rental rental) {
        Optional<Rental> existingRental = rentals.stream()
                .filter(r -> r.getId().equals(rental.getId()))
                .findFirst();

        if (existingRental.isPresent()) {
            int index = rentals.indexOf(existingRental.get());
            rentals.set(index, rental);
            return rental;
        } else {
            throw new RuntimeException("Rental with ID " + rental.getId() + " not found.");
        }
    }

    @Override
    public void deleteById(Long id) {

        rentals.remove(findById(id).get());
    }

    @Override
    public boolean existsById(Long id) {

        return rentals.stream().filter(i -> i.getId().equals(id)).count() == 1;
    }
}
