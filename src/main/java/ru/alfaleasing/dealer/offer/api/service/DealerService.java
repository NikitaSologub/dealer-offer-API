package ru.alfaleasing.dealer.offer.api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alfaleasing.dealer.offer.api.dto.DealerDTO;
import ru.alfaleasing.dealer.offer.api.repository.DealerRepository;

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

    public List<DealerDTO> getAllDealers() {
        return dealerRepository.findAll().stream()
            .map(dealer -> DealerDTO.builder()
                .uid(dealer.getUid().toString())
                .dealer(dealer.getName())
                .city(null)
                .inn(dealer.getInn())
                .kpp(dealer.getKpp())
                .region(dealer.getRegion())
                .address(null)
                .build())
            .collect(toList());
    }
}
