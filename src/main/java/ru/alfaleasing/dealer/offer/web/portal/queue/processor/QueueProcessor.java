package ru.alfaleasing.dealer.offer.web.portal.queue.processor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alfaleasing.dealer.offer.web.portal.queue.QueueSender;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class QueueProcessor {

    private final QueueSender queueSender;

    /**
     * Для отправки сообщений в очередь
     *
     * @param message данными о автомобилях в формате которые запишем в очередь
     */
    public void publishMessage(String message) {
        log.debug("Записываем сообщение в очередь: {}", message);
        System.out.println("Записываем сообщение в очередь:" + message);
//        queueSender.sendCarStock().send(MessageBuilder.withPayload(message)//todo
//            .setHeader("x-time-created", LocalDateTime.now())
//            .build());
    }
}