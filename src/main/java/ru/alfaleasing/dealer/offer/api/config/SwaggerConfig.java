package ru.alfaleasing.dealer.offer.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
            .useDefaultResponseMessages(false)
            .select()
            .apis(RequestHandlerSelectors.basePackage("ru.alfaleasing.dealer.offer.api.controller"))
            .build()
            .apiInfo(
                new ApiInfo(
                    "dealer-offer-api",
                    "Микросервис шлюз для следующих функции:\n" +
                        "\n" +
                        "Энд поинты для загрузки стоков дилерами\n" +
                        "Общение с фронтом\n" +
                        "Получение и передача данных остальным микросервисам\n" +
                        "Преобразование данных в необходимый формат\n" +
                        "Передача списка в MiniO\n" +
                        "Запись о переданном списке в RabbitMQ\n" +
                        "Запись о результате валидации стоков в базу\n" +
                        "Проверка в каталоге лизингового имущества забор данных по задаче\n",
                    "1.0",
                    null,
                    ApiInfo.DEFAULT_CONTACT,
                    null,
                    null,
                    Collections.emptyList())
            );
    }
}
