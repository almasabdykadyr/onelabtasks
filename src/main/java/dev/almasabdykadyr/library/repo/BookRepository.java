package dev.almasabdykadyr.library.repo;

import dev.almasabdykadyr.library.entity.Book;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends ListCrudRepository<Book, Long> {
}
