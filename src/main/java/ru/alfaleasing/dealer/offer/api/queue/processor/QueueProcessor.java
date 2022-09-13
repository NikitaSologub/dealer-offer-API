package ru.alfaleasing.dealer.offer.api.queue.processor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alfaleasing.dealer.offer.api.dto.StockDTO;
import ru.alfaleasing.dealer.offer.api.queue.QueueReceiver;
import ru.alfaleasing.dealer.offer.api.queue.QueueSender;

import java.nio.charset.StandardCharsets;
import java.util.List;

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
     * @param data данными о автомобилях в формате которые запишем в очередь
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
        String validCarsJsonString = new String((byte[]) message.getPayload(), StandardCharsets.UTF_8);
        System.out.println("Из очереди получили список валидных автомобилей (как строку JSON)= " + validCarsJsonString);

        try { // todo - Временное решение пока не определён общий формат данных
            List<StockDTO> list = objectMapper.readValue(validCarsJsonString, new TypeReference<List<StockDTO>>() {});
            System.out.println("Парсим из строки список объектов типа ExcelCarDTO");
            list.forEach(System.out::println);
        } catch (JsonProcessingException jsonProcessingException) {
            System.out.println("Не удалось разобрать тело запроса из Json в объекты ExcelCarDTO");
            log.info("Не удалось разобрать тело запроса из Json в объекты ExcelCarDTO");
        }
    }
}
