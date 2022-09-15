package ru.alfaleasing.dealer.offer.api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alfaleasing.dealer.offer.api.dto.StockDTO;
import ru.alfaleasing.dealer.offer.api.dto.TaskDTO;
import ru.alfaleasing.dealer.offer.api.stream.processor.QueueProcessor;

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
     * @param methodType способ загрузки данных (FILE, API)
     * @param salonId UUID конкретного дилера
     */
    public void loadStocksToMinioAndRabbit(List<StockDTO> stock, String methodType, UUID salonId) {
        log.info("Попали в сервис и пытаемся положить стоки в Rabbit и minIO {}", stock);
        System.out.println("methodType = " + methodType);
        System.out.println("salonId = " + salonId);
        String fileName = minIOService.writeFileToMinIO(stock, salonId);

        // 1) Берем из БД по salonId нужного дилера и создаём Task в котором будет информация из дилера
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTaskUid(UUID.randomUUID());
        taskDTO.setDealerUid(salonId);
        taskDTO.setDealerName("Автомир");
        taskDTO.setCity("Москва");
//        taskDTO.setDealer(new Dealer(salonId,"Автомир"));
        taskDTO.setUsed(false);
        taskDTO.setS3ObjectName(fileName);

        // 2) Записываем в RabbitMQ объект типа task
        queueProcessor.publishMessage(taskDTO);

        // 3) Записываем в базу данных объект типа task и ставим ему статус (типо в процессе)
    }
}
