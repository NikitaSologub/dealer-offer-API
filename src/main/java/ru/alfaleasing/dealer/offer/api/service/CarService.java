package ru.alfaleasing.dealer.offer.api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alfaleasing.dealer.offer.api.dto.StockDTO;
import ru.alfaleasing.dealer.offer.api.queue.processor.QueueProcessor;

import java.util.List;
import java.util.UUID;

/**
 * Сервис для работы с документами автомобилей
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class CarService {

    private final QueueProcessor queueProcessor;
    private final MinIOService minIOService;

    /**
     * Метод используется для загрузки стоков и помещения их в minIO и отправки объекта в RabbitMQ
     *
     * @param stock данные о автомобилях которые нужно загрузить
     * @return Запрос со списками валидных и не валидных автомобилей
     */
    public List<StockDTO> loadStocksToMinioAndRabbit(List<StockDTO> stock, String methodType, UUID salonId) {
        log.info("Попали в сервис и пытаемся положить стоки в Rabbit и minIO {}", stock);
        System.out.println("methodType = " + methodType);
        System.out.println("salonId = " + salonId);
        queueProcessor.publishMessage(stock);
        minIOService.writeFileToMinIO(stock);
        return stock;
    }
}
