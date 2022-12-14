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
import java.util.stream.Collectors;

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
    @Transactional
    public void receiverFromCSharpSystem(TaskResultDTO aggregationServerResponse) { //todo - рефакторинг
        log.info("aggregationServerResponse from C# systems= {}", aggregationServerResponse);


        Task taskFromDb = taskRepository.getTaskByUid(aggregationServerResponse.getTaskUid());
        if (taskFromDb == null) {
            log.info("Из из C# систем пришёл запрос на изменение статуса task с uuid которого не существует в базе");
            return;
        }

        TaskStatus currentStatus = aggregationServerResponse.getStatus();
        log.info("0.4. Смотрим статус Task из C# систем. TaskStatus = {}", currentStatus);

        if (TaskStatus.IN_WORK.equals(currentStatus)) {
            log.info("1.0. Попали в блок IN_WORK Смотрим сколько автомобилей содержат vin в массиве (все эти машины прошли проверку ГОИ)");

            log.info("1.1. Смотрим сколько автомобилей содержат vin != null (они прошли проверку ГОИ)");
            long carsMappedByGoiCount = aggregationServerResponse.getResults().stream()
                .filter(car -> Objects.nonNull(car.getVin()))
                .count();

            log.info("1.2 Объекту Task из БД  обновляем TaskStatus ={}, кол-во опубликованных машин ={} и jsonb как весь наш файл", currentStatus, carsMappedByGoiCount);
            taskFromDb.setStatus(currentStatus);
            taskFromDb.setOffersPublished(Math.toIntExact(carsMappedByGoiCount));
            taskFromDb.setTaskResult(aggregationServerResponse);
            Task changedTask = taskRepository.save(taskFromDb);
            log.info("1.3 Объект Task обновлён в БД  task ={}", changedTask);
        } else if (TaskStatus.DONE.equals(currentStatus) || TaskStatus.FAIL.equals(currentStatus)) {
            log.info("2.0. Попали в блок DONE и FAIL");

            log.info("2.1. Смотрим сколько автомобилей содержат status='published' (они прошли проверку ГОИ и КЛИ)");
            long publishedCars = aggregationServerResponse.getResults().stream()
                .filter(car -> Objects.nonNull(car.getVin()))
                .filter(car -> PUBLISHED.equals(car.getStatus()))
                .count();

            log.info("2.2 Объекту Task из БД  обновляем TaskStatus ={}, кол-во опубликованных машин ={} и jsonb как весь наш файл", currentStatus, publishedCars);
            taskFromDb.setStatus(currentStatus);
            taskFromDb.setOffersPublished(Math.toIntExact(publishedCars));
            taskFromDb.setTaskResult(aggregationServerResponse);
            Task changedTask = taskRepository.save(taskFromDb);
            log.info("2.3 Объект Task обновлён в БД  task ={}", changedTask);

            log.info("2.4 Все не опубликованные автомобили в ГОИ и КЛИ пишем а лог.");
            List<CarInfoDTO> notPublishedCars = aggregationServerResponse.getResults().stream()
                .filter(car -> !PUBLISHED.equals(car.getStatus()))
                .collect(Collectors.toList());
            log.info("NOT PUBLISHED CARS BY SOME REASON = {}", notPublishedCars);
        } else {
            log.info("3.0 Попали в блок ERROR. Из системы пришёл странный статус - не известно что с ним делать");
        }
    }
}
