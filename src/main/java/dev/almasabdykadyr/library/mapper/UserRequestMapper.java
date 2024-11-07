package dev.almasabdykadyr.library.mapper;

import dev.almasabdykadyr.library.dto.UserRequest;
import dev.almasabdykadyr.library.entity.Roles;
import dev.almasabdykadyr.library.entity.User;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Set;

@Component
public class UserRequestMapper implements Converter<UserRequest, User> {

    @Override
    public User convert(MappingContext<UserRequest, User> context) {
        var request = context.getSource();

        return User.builder()
                .email(request.email())
                .password(request.password())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .roles(Set.of(Roles.USER))
                .createdAt(LocalDateTime.now())
                .build();
    }
}
