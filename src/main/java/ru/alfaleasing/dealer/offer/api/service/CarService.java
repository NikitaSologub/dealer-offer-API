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

    private static final String JSON = ".json";
    private final QueueProcessor queueProcessor;
    private final MinIOService minIOService;

    /**
     * Метод используется для загрузки стоков и помещения их в minIO и отправки объекта в RabbitMQ
     *
     * @param stock данные о автомобилях которые нужно загрузить
     * @param methodType способ загрузки данных (FILE, API)
     * @param salonId UUID конкретного дилера
     */
    public UUID loadStocksToMinioAndRabbit(List<StockDTO> stock, String methodType, UUID salonId) {
        log.info("Попали в сервис и пытаемся положить стоки в Rabbit и minIO {}", stock);
        log.info("methodType = {} salonId = {}", methodType, salonId);

        log.info("1) Берем из БД по salonId нужного дилера и создаём Task в котором будет информация из дилера");
        // 1) Берем из БД по salonId нужного дилера и создаём Task в котором будет информация из дилера
        UUID taskUid = UUID.randomUUID();
        String fileName = salonId + JSON;

        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTaskUid(taskUid);
        taskDTO.setDealerUid(salonId);
        taskDTO.setDealerName("Автомир");
        taskDTO.setCity("Москва");
        taskDTO.setUsed(false);
        taskDTO.setS3ObjectName(fileName);

        log.info("1.1) Обновляем поле в БД (last_task_date) для объекта Connection и ставим туда дату создания таски (LocalDate.new())");
        // 1.1) Обновляем поле в БД (last_task_date) для объекта Connection и ставим туда дату создания таски (LocalDate.new())

        log.info("2) Записываем в базу данных объект типа task и ставим ему статус (типо в процессе)");
        // 2) Записываем в базу данных объект типа task и ставим ему статус (типо в процессе)

        log.info("3) Кладём этот объект в minIO с UUID таски (UUID taskUid = UUID.randomUUID();)");
        // 3) Кладём этот объект в minIO с UUID таски (UUID taskUid = UUID.randomUUID();)
        minIOService.writeFileToMinIO(stock, fileName);

        // 4) Записываем в RabbitMQ объект типа task
        log.info("4) Записываем в RabbitMQ объект типа task");
        queueProcessor.publishMessage(taskDTO);

        log.info("5) Возвращаем UUID нашей созданной таски");
        // 5) Возвращаем UUID нашей созданной таски
        return taskUid;
    }
}
