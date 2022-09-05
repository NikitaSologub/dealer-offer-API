package ru.alfaleasing.dealer.offer.api.queue.processor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alfaleasing.dealer.offer.api.queue.QueueSender;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class QueueProcessor {

    private final QueueSender queueSender;

    /**
     * Для отправки сообщений в очередь
     *
     * @param data данными о автомобилях в формате которые запишем в очередь
     */
    public void publishMessage(Object data) {
        log.debug("Записываем сообщение в очередь: {}", data);
        queueSender.sendCarStock().send(MessageBuilder.withPayload(data)
//            .setHeader("x-time-created", LocalDateTime.now())
            .build());
    }
}
