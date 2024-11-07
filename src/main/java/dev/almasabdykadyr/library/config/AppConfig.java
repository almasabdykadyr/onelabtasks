package dev.almasabdykadyr.library.config;

import dev.almasabdykadyr.library.mapper.AuthorMapper;
import dev.almasabdykadyr.library.mapper.BookMapper;
import dev.almasabdykadyr.library.mapper.UserRequestMapper;
import dev.almasabdykadyr.library.security.JwtProperties;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableScheduling
@EnableTransactionManagement
@EnableWebSecurity
@EnableConfigurationProperties(JwtProperties.class)
@Configuration
@RequiredArgsConstructor
public class AppConfig {

    @Bean
    public ModelMapper mapper(AuthorMapper authorMapper, UserRequestMapper userRequestMapper, BookMapper bookMapper) {
        ModelMapper mapper = new ModelMapper();
        mapper.addConverter(authorMapper);
        mapper.addConverter(userRequestMapper);
        mapper.addConverter(bookMapper);
        return mapper;
    }
}
