package ru.alfaleasing.dealer.offer.api.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface QueueReceiver {

    String C_SHARP_EXCHANGE = "c_sharp_exchange";

    @Input(C_SHARP_EXCHANGE)
    SubscribableChannel readStatusFromCSharpSystem();
}
