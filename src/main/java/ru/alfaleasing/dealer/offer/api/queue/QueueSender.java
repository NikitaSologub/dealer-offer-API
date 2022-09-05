package ru.alfaleasing.dealer.offer.api.queue;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface QueueSender {

    String DEALER_CAR_STOCK = "dealer-car-stock";

    @Output(DEALER_CAR_STOCK)
    MessageChannel sendCarStock();
}
