package ru.alfaleasing.dealer.offer.api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alfaleasing.dealer.offer.api.dto.DealerInDbDTO;
import ru.alfaleasing.dealer.offer.api.entity.Connection;
import ru.alfaleasing.dealer.offer.api.repository.ConnectionRepository;
import ru.alfaleasing.dealer.offer.api.repository.DealerRepository;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Сервис для работы с контрагентами
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class DealerService {

    private final DealerRepository dealerRepository;
    private final ConnectionRepository connectionRepository;

    /**
     * Вернуть всех контрагентов из БД
     */
    @Transactional(readOnly = true)
    public List<DealerInDbDTO> getAllDealers() {
        List<Connection> connections = connectionRepository.findAll();

        return dealerRepository.findAll().stream()
            .map(dealer -> DealerInDbDTO.builder()
                .uid(dealer.getUid().toString())
                .dealer(dealer.getName())
                .inn(dealer.getInn())
                .kpp(dealer.getKpp())
                .region(dealer.getRegion())
                .createDate(dealer.getCreateDate().toString())
                .createAuthor(dealer.getCreateAuthor())
                .lastUpdated(
                    connections.stream()
                        .filter(conn -> conn.getDealer().getUid().equals(dealer.getUid()))
                        .filter(conn -> conn.getLastTaskDate() != null)
                        .sorted(Comparator.comparing(Connection::getLastTaskDate).reversed())
                        .map(Connection::getLastTaskDate)
                        .peek(some -> System.out.println("some_thing" + some))
                        .map(LocalDateTime::toString)
                        .findFirst().orElse(null)
                )
                .build())
            .collect(toList());
    }
}
