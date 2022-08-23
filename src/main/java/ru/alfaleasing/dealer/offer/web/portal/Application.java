package ru.alfaleasing.dealer.offer.web.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import ru.alfaleasing.dealer.offer.web.portal.queue.QueueSender;

@SpringBootApplication
@EnableFeignClients
@EnableBinding({QueueSender.class})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}