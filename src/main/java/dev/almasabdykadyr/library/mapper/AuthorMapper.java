package dev.almasabdykadyr.library.mapper;

import dev.almasabdykadyr.library.dto.AuthorRequest;
import dev.almasabdykadyr.library.entity.Author;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AuthorMapper implements Converter<AuthorRequest, Author> {

    @Override
    public Author convert(MappingContext<AuthorRequest, Author> mappingContext) {
        var request = mappingContext.getSource();

        return Author.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
