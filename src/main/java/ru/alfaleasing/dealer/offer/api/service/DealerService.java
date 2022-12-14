package ru.alfaleasing.dealer.offer.api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alfaleasing.dealer.offer.api.constant.LoadingType;
import ru.alfaleasing.dealer.offer.api.dto.DealerDTO;
import ru.alfaleasing.dealer.offer.api.dto.DealerInDbDTO;
import ru.alfaleasing.dealer.offer.api.entity.Dealer;
import ru.alfaleasing.dealer.offer.api.mapper.ConnectionMapper;
import ru.alfaleasing.dealer.offer.api.mapper.DealerMapper;
import ru.alfaleasing.dealer.offer.api.repository.DealerRepository;

import java.time.LocalDateTime;
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
    private final DealerMapper dealerMapper;
    private final ConnectionMapper connectionMapper;

    /**
     * Вернуть всех контрагентов из БД
     *
     * @return Список контрагентов которые есть в базе с датами последних загрузок стоков по ним
     */
    @Transactional(readOnly = true)
    public List<DealerInDbDTO> getAllDealers() {
        return dealerRepository.findAllWithConnections().stream()
            .map(dealerMapper::toDealerInDbDTO)
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
        LocalDateTime now = LocalDateTime.now();
        Dealer dealerForSave = dealerMapper.toDealer(dealer, createdAuthor, now);
        dealerForSave.addConnection(connectionMapper.toConnection(LoadingType.FILE, createdAuthor, now));
        dealerForSave.addConnection(connectionMapper.toConnection(LoadingType.API, createdAuthor, now));
        return dealerRepository.save(dealerForSave).getUid();
    }
}
