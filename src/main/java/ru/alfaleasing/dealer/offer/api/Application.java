package ru.alfaleasing.dealer.offer.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import ru.alfaleasing.dealer.offer.api.stream.QueueReceiver;
import ru.alfaleasing.dealer.offer.api.stream.QueueSender;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableBinding({QueueSender.class, QueueReceiver.class})
@EnableSwagger2
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
