package ru.alfaleasing.dealer.offer.api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alfaleasing.dealer.offer.api.controller.param.TaskStatus;
import ru.alfaleasing.dealer.offer.api.dto.StockDTO;
import ru.alfaleasing.dealer.offer.api.dto.TaskDTO;
import ru.alfaleasing.dealer.offer.api.model.Connection;
import ru.alfaleasing.dealer.offer.api.model.Dealer;
import ru.alfaleasing.dealer.offer.api.model.Task;
import ru.alfaleasing.dealer.offer.api.repository.ConnectionRepository;
import ru.alfaleasing.dealer.offer.api.repository.DealerRepository;
import ru.alfaleasing.dealer.offer.api.repository.TaskRepository;
import ru.alfaleasing.dealer.offer.api.stream.processor.QueueProcessor;

import java.time.LocalDate;
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
    private final DealerRepository dealerRepository;
    private final ConnectionRepository connectionRepository;
    private final TaskRepository taskRepository;

    /**
     * Метод используется для загрузки стоков и помещения их в minIO и отправки объекта в RabbitMQ
     *
     * @param stock      данные о автомобилях которые нужно загрузить
     * @param methodType способ загрузки данных (FILE, API, EXTERNAL_API, LINK)
     * @param salonId    UUID конкретного дилера
     */
    public UUID loadStocksToMinioAndRabbit(List<StockDTO> stock, String methodType, UUID salonId, String clientId) {
        log.info("methodType = {} salonId = {}  clientId = {}", methodType, salonId, clientId);
        LocalDate now = LocalDate.now();

        log.info("1) Берем из БД по salonId нужного дилера и создаём Task в котором будет информация из дилера");
        Dealer dealer = dealerRepository.getDealerByUid(salonId); //3fa85f64-5717-4562-b3fc-2c963f66afa6 - avtomir

        if (methodType != null && dealer != null) {
            Connection currentConnection = connectionRepository.getConnectionsByDealer(dealer).stream()
                .filter(conn -> methodType.equalsIgnoreCase(conn.getType().toString()))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

            Task task = Task.builder()
                .uid(UUID.randomUUID())
                .connection(currentConnection)
                .dealer(dealer)
                .createDate(now)
                .author(clientId)
                .status(TaskStatus.IN_WORK)
                .isUsed(false)
                .offersReceived(stock.size())
                .offersPublished(0)
                .build();

            log.info("2) Записываем в базу данных объект типа task и ставим ему статус (IN_WORK)");
            System.out.println("task = " + task);
            Task taskInDb = taskRepository.save(task);
            System.out.println("taskInDb = " + taskInDb);

            log.info("3) Обновляем поле в БД (last_task_date) для объекта Connection и ставим туда дату создания таски (LocalDate.new())");
            currentConnection.setLastTaskDate(now);
            connectionRepository.save(currentConnection);

            log.info("4) Создаём DTO для Task чтобы отправить его в Rabbit");
            TaskDTO taskDTO = TaskDTO.builder()
                .taskUid(taskInDb.getUid())
                .dealerUid(dealer.getUid())
                .dealerName(dealer.getName())
                .city(null)
                .isUsed(false)
                .s3ObjectName(taskInDb.getUid() + JSON)
                .build();

            log.info("5) Кладём все стоки в minIO с UUID таски (UUID taskUid = UUID.randomUUID();)");
            minIOService.writeFileToMinIO(stock, taskInDb.getUid() + JSON);

            log.info("6) Записываем в RabbitMQ объект типа taskDTO");
            queueProcessor.publishMessage(taskDTO);

            log.info("7) Возвращаем UUID нашей созданной таски");
            return taskInDb.getUid();

        } else {
            throw new IllegalArgumentException("methodType не определён или не валиден");
        }
    }
}
