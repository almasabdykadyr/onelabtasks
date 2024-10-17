package dev.almasabdykadyr.library.repo;

import dev.almasabdykadyr.library.entity.Author;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends AbstractRepository<Author, Long> {
}
