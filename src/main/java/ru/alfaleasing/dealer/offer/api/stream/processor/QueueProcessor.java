package ru.alfaleasing.dealer.offer.api.stream.processor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alfaleasing.dealer.offer.api.dto.TaskResponseFromCSharpSystemDTO;
import ru.alfaleasing.dealer.offer.api.dto.status.Status;
import ru.alfaleasing.dealer.offer.api.stream.QueueReceiver;
import ru.alfaleasing.dealer.offer.api.stream.QueueSender;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class QueueProcessor {

    public static final String PUBLISHED = "published";
    private final QueueSender queueSender;
    private final ObjectMapper objectMapper;

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
    public void receiverFromCSharpSystem(String message) {
        log.info("Принимаем из C# систем статусы и сохраняем результаты в БД");
        log.info("message = {}", message);
        try {
            TaskResponseFromCSharpSystemDTO task = objectMapper.readValue(message, TaskResponseFromCSharpSystemDTO.class);
            log.info("task = {}", task);


            //1. если пришёл статус IN_WORK
            if (Status.IN_WORK.equals(task.getStatus())) {
                // 1.1 Смотрим сколько автомобилей содержат vin в массиве (все эти машины будут проходить проверку ГОИ)
                log.info("Попали в блок IN_WORK");
                long publishedCarsCount = task.getResults().stream()
                    .filter(car -> Objects.nonNull(car.getVin()))
                    .count();

                //1.2 Достаём из БД объект типа Task и изменяем ему Status, кол-во опубликованных машин и записываем jsonb как весь наш файл

            } else if (Status.DONE.equals(task.getStatus()) || Status.FAILED.equals(task.getStatus())) {
                //2. если пришли статусы DONE и FAILED
                log.info("Попали в блок DONE и FAILED");

                // 2.1 Смотрим сколько автомобилей опубликовано
                long publishedCarsCount = task.getResults().stream()
                    .filter(a -> PUBLISHED.equals(a.getStatus()))
                    .count();

                //2.2 Достаём из БД объект типа Task и изменяем ему Status, кол-во опубликованных машин и записываем jsonb как весь наш файл

            } else {
                //3. если пришёл левый статус - хз что делать
                log.info("Попали в блок ERROR");
                log.info("Из системы пришёл странный статус - хз чо с ним делать");
            }

        } catch (JsonProcessingException e) {
            log.info("Не удалось разобрать тело запроса из Json в объект TaskResponseFromCSharpSystem");
        }
    }
}
