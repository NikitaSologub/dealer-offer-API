package ru.alfaleasing.dealer.offer.api.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alfaleasing.dealer.offer.api.constant.LoadingType;
import ru.alfaleasing.dealer.offer.api.dto.StockDTO;
import ru.alfaleasing.dealer.offer.api.entity.Connection;
import ru.alfaleasing.dealer.offer.api.entity.Task;
import ru.alfaleasing.dealer.offer.api.exception.EntityNotFoundException;
import ru.alfaleasing.dealer.offer.api.mapper.TaskMapper;
import ru.alfaleasing.dealer.offer.api.repository.ConnectionRepository;
import ru.alfaleasing.dealer.offer.api.repository.DealerRepository;
import ru.alfaleasing.dealer.offer.api.repository.TaskRepository;
import ru.alfaleasing.dealer.offer.api.service.CarService;
import ru.alfaleasing.dealer.offer.api.service.MinIOService;
import ru.alfaleasing.dealer.offer.api.stream.processor.QueueProcessor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static ru.alfaleasing.dealer.offer.api.constant.AppsConstant.JSON;

/**
 * Сервис для работы с документами автомобилей
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class CarServiceImpl implements CarService {

    public static final String CONNECTION_NAME = "Connection";
    public static final String LOADING_TYPE_NAME = "LoadingType";
    public static final String DEALER_NAME = "Dealer";
    public static final String SALON_ID_NAME = "salonId";

    private final QueueProcessor queueProcessor;
    private final MinIOService minIOService;
    private final DealerRepository dealerRepository;
    private final ConnectionRepository connectionRepository;
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    /**
     * Метод используется для загрузки стоков и помещения их в minIO и отправки объекта в RabbitMQ
     *
     * @param stock      данные о автомобилях которые нужно загрузить
     * @param methodType способ загрузки данных (FILE, API)
     * @param salonId    UUID конкретного дилера
     */
    @Override
    @SneakyThrows
    public UUID loadStocksToMinioAndRabbit(List<StockDTO> stock, String methodType, UUID salonId, String clientId) {
        log.info("methodType = {} salonId = {}  clientId = {}", methodType, salonId, clientId);
        LocalDateTime now = LocalDateTime.now();
        LoadingType taskLoadingType = LoadingType.valueOf(methodType);
        return dealerRepository.findDealerByUid(salonId)
            .map(dealer -> {
                Connection currentConnection = connectionRepository.getConnectionsByDealer(dealer.getUid()).stream()
                    .filter(connection -> taskLoadingType == connection.getType())
                    .findFirst()
                    .orElseThrow(() -> new EntityNotFoundException(CONNECTION_NAME, LOADING_TYPE_NAME, methodType));

                Task savedTask = taskRepository.save(taskMapper.getTask(stock, clientId, now, dealer, currentConnection, UUID.randomUUID()));
                currentConnection.setLastTaskDate(now);
                connectionRepository.save(currentConnection);

                minIOService.writeFileToMinIO(stock, savedTask.getUid() + JSON);
                queueProcessor.publishMessage(taskMapper.getTaskDTO(dealer, savedTask));
                return savedTask.getUid();

            })
            .orElseThrow(() -> new EntityNotFoundException(DEALER_NAME, SALON_ID_NAME, salonId));
    }
}
