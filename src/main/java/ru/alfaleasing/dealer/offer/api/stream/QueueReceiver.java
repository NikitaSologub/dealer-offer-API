package ru.alfaleasing.dealer.offer.api.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface QueueReceiver {

    String DEALER_CAR_STOCK = "dealer_offer_api";
    String C_SHARP_EXCHANGE = "c_sharp_exchange";

    @Input(DEALER_CAR_STOCK)
    SubscribableChannel readStockFromCliCheckingSystem();

    @Input(C_SHARP_EXCHANGE)
    SubscribableChannel readStatusFromCSharpSystem();
}
