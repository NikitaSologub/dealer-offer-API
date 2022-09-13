package ru.alfaleasing.dealer.offer.api.queue.processor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alfaleasing.dealer.offer.api.dto.TaskDTO;
import ru.alfaleasing.dealer.offer.api.queue.QueueReceiver;
import ru.alfaleasing.dealer.offer.api.queue.QueueSender;

import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class QueueProcessor {

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
     * Для получения сообщений из очереди
     */
    @StreamListener(QueueReceiver.DEALER_CAR_STOCK)
    public void receiver(Message message) {
        log.info("Принимаем сообщение из очереди: {}", message);
        System.out.println("Принимаем сообщение из очереди: " + message);
        //        MessageHeaders headers = message.getHeaders();
//        System.out.println("getHeaders:" + message.getHeaders());
//        UUID messageUuid = headers.getId();
//        System.out.println("messageUuid = " + messageUuid);
//        long timestamp = headers.getTimestamp();
//        LocalDateTime receivingLocalDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp),
//                TimeZone.getDefault().toZoneId());
//        System.out.println("receivingLocalDateTime = " + receivingLocalDateTime);
        String jsonBody = new String((byte[]) message.getPayload(), StandardCharsets.UTF_8);
        System.out.println("Из очереди получили: (как строку JSON)= " + jsonBody);

        try { // todo - Временное решение пока не определён общий формат данных
            TaskDTO taskDTO = objectMapper.readValue(jsonBody, TaskDTO.class);
            System.out.println("Парсим из строки в объект типа TaskDTO");
            System.out.println("taskDTO = " + taskDTO);
        } catch (JsonProcessingException jsonProcessingException) {
            System.out.println("Не удалось разобрать тело запроса из Json в объект taskDTO");
            log.info("Не удалось разобрать тело запроса из Json в объекты ExcelCarDTO");
        }
    }
}
