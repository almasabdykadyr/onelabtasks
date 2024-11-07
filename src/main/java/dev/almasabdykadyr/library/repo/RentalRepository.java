package dev.almasabdykadyr.library.repo;

import dev.almasabdykadyr.library.entity.RentStatus;
import dev.almasabdykadyr.library.entity.Rental;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {

    @Modifying
    @Query("UPDATE Rental " +
            "SET status =:status " +
            "WHERE id in :ids")
    void updateByIds(List<Long> ids, RentStatus status);

    @Query("select Rental from Rental " +
            "where :userId is not null and userId = :userId" +
            " or :bookId is not null and bookId = :bookId"
    )
    List<Rental> findByUserIdOrBookId(Long userId, Long bookId);

    @Modifying
    @Query("update Rental " +
            "set status = :status " +
            "where id =:id")
    void updateByIdAndStatus(RentStatus status, Long id);

    List<Rental> findAllByStatusAndDueDateBefore(RentStatus status, @NotNull LocalDate dueDate);
}
