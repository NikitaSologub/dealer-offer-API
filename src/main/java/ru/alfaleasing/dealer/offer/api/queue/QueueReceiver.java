package ru.alfaleasing.dealer.offer.api.queue;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface QueueReceiver {

    String DEALER_CAR_STOCK = "dealer-car-stock";

    @Input(DEALER_CAR_STOCK)
    SubscribableChannel readCarFromStock();
}
