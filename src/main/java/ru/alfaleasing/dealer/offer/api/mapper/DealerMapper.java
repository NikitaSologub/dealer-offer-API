package ru.alfaleasing.dealer.offer.api.mapper;

import org.springframework.stereotype.Component;
import ru.alfaleasing.dealer.offer.api.dto.DealerDTO;
import ru.alfaleasing.dealer.offer.api.dto.DealerInDbDTO;
import ru.alfaleasing.dealer.offer.api.entity.Connection;
import ru.alfaleasing.dealer.offer.api.entity.Dealer;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Objects;
import java.util.UUID;

@Component
public class DealerMapper {

    public Dealer toDealer(DealerDTO dealer, String createdAuthor, LocalDateTime time) {
        return Dealer.builder()
                .uid(UUID.fromString(dealer.getUid()))
                .name(dealer.getDealer())
                .region(dealer.getRegion())
                .location(dealer.getAddress())
                .inn(dealer.getInn())
                .kpp(dealer.getKpp())
                .createDate(time)
                .createAuthor(createdAuthor)
                .isDeleted(false)
                .build();
    }

    public DealerInDbDTO toDealerInDbDTO(Dealer dealer) {
        return DealerInDbDTO.builder()
                .uid(dealer.getUid().toString())
                .dealer(dealer.getName())
                .inn(dealer.getInn())
                .kpp(dealer.getKpp())
                .region(dealer.getRegion())
                .createDate(dealer.getCreateDate().toString())
                .createAuthor(dealer.getCreateAuthor())
                .lastUpdated(
                        dealer.getConnections().stream()
                                .filter(conn -> Objects.nonNull(conn.getLastTaskDate()))
                                .sorted(Comparator.comparing(Connection::getLastTaskDate).reversed())
                                .map(Connection::getLastTaskDate)
                                .map(LocalDateTime::toString)
                                .findFirst()
                                .orElse(null)
                )
                .build();
    }
}
