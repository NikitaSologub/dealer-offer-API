package ru.alfaleasing.dealer.offer.api.service;

import org.springframework.transaction.annotation.Transactional;
import ru.alfaleasing.dealer.offer.api.dto.StockDTO;

import java.util.List;
import java.util.UUID;

public interface CarService {
    @Transactional
    UUID loadStocksToMinioAndRabbit(List<StockDTO> stock, String methodType, UUID salonId, String clientId);
}
