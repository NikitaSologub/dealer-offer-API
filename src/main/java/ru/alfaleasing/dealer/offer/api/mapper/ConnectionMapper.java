package ru.alfaleasing.dealer.offer.api.mapper;

import org.springframework.stereotype.Component;
import ru.alfaleasing.dealer.offer.api.constant.LoadingType;
import ru.alfaleasing.dealer.offer.api.entity.Connection;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class ConnectionMapper {

    public Connection toConnection(LoadingType type, String createdAuthor, LocalDateTime time) {
        return Connection.builder()
            .uid(UUID.randomUUID())
            .createAuthor(createdAuthor)
            .type(type)
            .createDate(time)
            .lastTaskDate(null)
            .isUsed(true)
            .build();
    }
}
