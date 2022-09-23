package ru.alfaleasing.dealer.offer.api.service;

import org.springframework.transaction.annotation.Transactional;
import ru.alfaleasing.dealer.offer.api.dto.DealerDTO;
import ru.alfaleasing.dealer.offer.api.dto.DealerInDbDTO;

import java.util.List;
import java.util.UUID;

public interface DealerService {
    @Transactional(readOnly = true)
    List<DealerInDbDTO> getAllDealers();

    @Transactional
    UUID loadDealer(DealerDTO dealer, String createdAuthor);
}
