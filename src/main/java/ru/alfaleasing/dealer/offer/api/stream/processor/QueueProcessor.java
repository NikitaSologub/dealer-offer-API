package ru.alfaleasing.dealer.offer.api.stream.processor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alfaleasing.dealer.offer.api.constant.TaskStatus;
import ru.alfaleasing.dealer.offer.api.dto.CarInfoDTO;
import ru.alfaleasing.dealer.offer.api.dto.TaskResultDTO;
import ru.alfaleasing.dealer.offer.api.entity.Task;
import ru.alfaleasing.dealer.offer.api.repository.TaskRepository;
import ru.alfaleasing.dealer.offer.api.stream.QueueReceiver;
import ru.alfaleasing.dealer.offer.api.stream.QueueSender;

import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class QueueProcessor {

    public static final String PUBLISHED = "published";
    private final TaskRepository taskRepository;
    private final QueueSender queueSender;

    /**
     * Для отправки сообщений в очередь
     *
     * @param data данные о таске которые пишем в очередь
     */
    public void publishMessage(Object data) {
        log.info("Записываем сообщение в очередь: {}", data);
        queueSender.sendCarStock().send(MessageBuilder.withPayload(data)
            .build());
    }

    /**
     * Для получения сообщений из С# системы
     */
    @StreamListener(QueueReceiver.C_SHARP_EXCHANGE)
    public void receiverFromCSharpSystem(TaskResultDTO aggregationServerResponse) {
        log.info("aggregationServerResponse from C# systems= {}", aggregationServerResponse);

        taskRepository.findTaskByUid(aggregationServerResponse.getTaskUid()).ifPresent(task -> {
            TaskStatus currentStatus = aggregationServerResponse.getStatus();

            if (TaskStatus.IN_WORK == currentStatus) {
                task.setStatus(currentStatus);
                task.setOffersPublished(0);
                task.setTaskResult(aggregationServerResponse);

            } else if (TaskStatus.DONE == currentStatus) {
                long publishedCars = aggregationServerResponse.getResults().stream()
                    .filter(Objects::nonNull)
                    .filter(car -> PUBLISHED.equals(car.getStatus()))
                    .count();
                task.setStatus(currentStatus);
                task.setOffersPublished(Integer.parseInt(String.valueOf(publishedCars)));
                task.setTaskResult(aggregationServerResponse);

                List<CarInfoDTO> notPublishedCars = aggregationServerResponse.getResults().stream()
                    .filter(Objects::nonNull)
                    .filter(car -> !PUBLISHED.equals(car.getStatus()))
                    .collect(toList());
                log.info("NOT PUBLISHED CARS BY SOME REASON = {}", notPublishedCars);

            } else if (TaskStatus.FAIL == currentStatus) {
                task.setStatus(currentStatus);
                task.setOffersPublished(0);
                task.setTaskResult(aggregationServerResponse);

            } else {
                log.info("ERROR. Incorrect TaskStatus from - aggregation service");
            }
            Task savedTask = taskRepository.save(task);
            log.info("Task by uid={} was updated with status-{} data={}", savedTask.getUid(), currentStatus, savedTask);
        });
    }
}
