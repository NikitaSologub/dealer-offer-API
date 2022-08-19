package ru.alfaleasing.dealer.offer.web.portal.queue;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface QueueListener {
    @Output("dealer-car-stock-exchange") // Название exchange
    MessageChannel sendMessages(); // этим методом мы будем посылать сообщения
}