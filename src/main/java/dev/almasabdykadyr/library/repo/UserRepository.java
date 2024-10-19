package dev.almasabdykadyr.library.repo;

import dev.almasabdykadyr.library.entity.User;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ListCrudRepository<User, Long> {
}
