package ru.alfaleasing.dealer.offer.api.mapper;

import org.springframework.stereotype.Component;
import ru.alfaleasing.dealer.offer.api.constant.TaskStatus;
import ru.alfaleasing.dealer.offer.api.dto.CarInfoDTO;
import ru.alfaleasing.dealer.offer.api.dto.StockDTO;
import ru.alfaleasing.dealer.offer.api.dto.TaskDTO;
import ru.alfaleasing.dealer.offer.api.dto.TaskResultDTO;
import ru.alfaleasing.dealer.offer.api.entity.Connection;
import ru.alfaleasing.dealer.offer.api.entity.Dealer;
import ru.alfaleasing.dealer.offer.api.entity.Task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;
import static ru.alfaleasing.dealer.offer.api.constant.AppsConstant.JSON;

@Component
public class TaskMapper {

    String BEFORE_GOI_AND_CLI = "before_goi_and_cli";

    public Task getTask(List<StockDTO> stock, String clientId, LocalDateTime now, Dealer dealer, Connection connection, UUID taskUid) {
        return Task.builder()
            .uid(taskUid)
            .connection(connection)
            .dealer(dealer)
            .createDate(now)
            .author(clientId)
            .status(TaskStatus.IN_WORK)
            .isUsed(false)
            .offersReceived(stock.size())
            .taskResult(TaskResultDTO.builder()
                .taskUid(taskUid)
                .status(TaskStatus.IN_WORK)
                .results(stock.stream()
                    .map(car -> CarInfoDTO.builder()
                        .vin(car.getVin())
                        .status(BEFORE_GOI_AND_CLI)
                        .build())
                    .collect(toList()))
                .build())
            .offersPublished(0)
            .build();
    }

    public TaskDTO getTaskDTO(Dealer dealer, Task task) {
        return TaskDTO.builder()
            .taskUid(task.getUid())
            .dealerUid(dealer.getUid())
            .dealerName(dealer.getName())
            .city(null)
            .isUsed(false)
            .s3ObjectName(task.getUid() + JSON)
            .build();
    }
}
