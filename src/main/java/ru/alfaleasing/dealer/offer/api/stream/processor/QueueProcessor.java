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
    public void receiverFromCSharpSystem(TaskResultDTO aggregationServerResponse) { //todo - рефакторинг + logs
        log.info("aggregationServerResponse from C# systems= {}", aggregationServerResponse);


//        Optional<Task> taskFromDb1 = taskRepository.findTaskByUid(aggregationServerResponse.getTaskUid());
        taskRepository.findTaskByUid(aggregationServerResponse.getTaskUid()).ifPresent(task -> {
            TaskStatus currentStatus = aggregationServerResponse.getStatus();

            if (TaskStatus.IN_WORK == currentStatus) {
                task.setStatus(currentStatus);
                task.setTaskResult(aggregationServerResponse);
            } else if (TaskStatus.DONE == currentStatus || TaskStatus.FAIL == currentStatus) {
                log.info("2.0. Попали в блок DONE и FAIL");

                log.info("2.1. Смотрим сколько автомобилей содержат status='published' (они прошли проверку ГОИ и КЛИ)");
                long publishedCars = aggregationServerResponse.getResults().stream()
                        .filter(Objects::nonNull)
                        .filter(car -> PUBLISHED.equals(car.getStatus()))
                        .count();

                log.info("2.2 Объекту Task из БД  обновляем TaskStatus ={}, кол-во опубликованных машин ={} и jsonb как весь наш файл", currentStatus, publishedCars);
                task.setStatus(currentStatus);
                task.setOffersPublished(Math.toIntExact(publishedCars)); //todo Integer.parseInt();
                task.setTaskResult(aggregationServerResponse);

                List<CarInfoDTO> notPublishedCars = aggregationServerResponse.getResults().stream()
                        .filter(Objects::nonNull)
                        .filter(car -> !PUBLISHED.equals(car.getStatus()))
                        .collect(toList());
                log.info("NOT PUBLISHED CARS BY SOME REASON = {}", notPublishedCars);
            } else {
                log.info("3.0 Попали в блок ERROR. Из системы пришёл странный статус - не известно что с ним делать");
            }
            Task changedTask = taskRepository.save(task);
            log.info("");//todo
        });
    }
}
