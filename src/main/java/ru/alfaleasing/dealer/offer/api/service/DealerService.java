package ru.alfaleasing.dealer.offer.api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alfaleasing.dealer.offer.api.controller.param.LoadingType;
import ru.alfaleasing.dealer.offer.api.dto.DealerDTO;
import ru.alfaleasing.dealer.offer.api.dto.DealerInDbDTO;
import ru.alfaleasing.dealer.offer.api.entity.Connection;
import ru.alfaleasing.dealer.offer.api.entity.Dealer;
import ru.alfaleasing.dealer.offer.api.repository.ConnectionRepository;
import ru.alfaleasing.dealer.offer.api.repository.DealerRepository;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

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
     *
     * @return Список контрагентов которые есть в базе с датами последних загрузок стоков по ним
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
                        .map(LocalDateTime::toString)
                        .findFirst().orElse(null)
                )
                .build())
            .collect(toList());
    }

    /**
     * Загрузить дилера из 1С (CRM) и создать для него Connections для API и FILE
     *
     * @param dealer        Объект контрагента который вносим в БД
     * @param createdAuthor Инициатор внесения контрагента в БД
     * @return UUID созданного контрагента в БД
     */
    @Transactional
    public UUID loadDealer(DealerDTO dealer, String createdAuthor) {
        log.info("Пытаемся внести в БД для createdAuthor={} нового контрагента={} ", createdAuthor, dealer);
        Dealer dealerInDb = loadDealerToDb(dealer, createdAuthor);
        Connection fileConnection = loadConnectionToDb(dealerInDb, createdAuthor, LoadingType.FILE);
        Connection apiConnection = loadConnectionToDb(dealerInDb, createdAuthor, LoadingType.API);
        log.info("Создали в БД запись нового дилера {} и два Connection для него: API={} FILE={}", dealerInDb, apiConnection, fileConnection);
        return dealerInDb.getUid();
    }

    /**
     * Сохранить в БД дилера из 1С (CRM)
     *
     * @param dealer        Дилер из 1С (CRM) системы который хотим сохранить в БД
     * @param createdAuthor Инициатор создания объекта Dealer
     * @return объект контрагента из БД
     */
    private Dealer loadDealerToDb(DealerDTO dealer, String createdAuthor) {
        return dealerRepository.save(Dealer.builder()
            .uid(UUID.randomUUID())
            .name(dealer.getDealer())
            .region(dealer.getRegion())
            .location(dealer.getAddress())
            .inn(dealer.getInn())
            .kpp(dealer.getKpp())
            .createDate(LocalDateTime.now())
            .createAuthor(createdAuthor)
            .isDeleted(false)
            .build());
    }

    /**
     * Создать для конкретного дилера в БД конкретный Connection
     *
     * @param dealer        Существующий дилер в БД
     * @param type          Какого типа объект Connection нужно создать
     * @param createdAuthor Инициатор создания объекта Connection
     * @return объект Connection нужного типа для конкретного контрагента
     */
    private Connection loadConnectionToDb(Dealer dealer, String createdAuthor, LoadingType type) {
        return connectionRepository.save(Connection.builder()
            .uid(UUID.randomUUID())
            .dealer(dealer)
            .isUsed(true)
            .createAuthor(createdAuthor)
            .createDate(dealer.getCreateDate())
            .lastTaskDate(null)
            .type(type)
            .build());
    }
}
