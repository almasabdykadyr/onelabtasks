package dev.almasabdykadyr.library.repo;

import dev.almasabdykadyr.library.entity.Book;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends AbstractRepository<Book, Long> {
}
