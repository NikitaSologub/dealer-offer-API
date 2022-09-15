package ru.alfaleasing.dealer.offer.api.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface QueueSender {

    String DEALER_CAR_STOCK = "dealer_offer_api";

    @Output(DEALER_CAR_STOCK)
    MessageChannel sendCarStock();
}
