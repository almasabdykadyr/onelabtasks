package dev.almasabdykadyr.library.mapper;

import dev.almasabdykadyr.library.dto.BookRequest;
import dev.almasabdykadyr.library.entity.Book;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class BookMapper implements Converter<BookRequest, Book> {

    @Override
    public Book convert(MappingContext<BookRequest, Book> context) {

        var request = context.getSource();

        return Book.builder()
                .isbn(request.isbn())
                .title(request.title())
                .description(request.description())
                .publishedAt(request.publishedAt())
                .authorId(request.authorId())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
