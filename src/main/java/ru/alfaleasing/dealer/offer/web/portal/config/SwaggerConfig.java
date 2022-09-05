package ru.alfaleasing.dealer.offer.web.portal.config;

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
            .apis(RequestHandlerSelectors.basePackage("ru.alfaleasing.dealer.offer.web.portal.controller"))
            .build()
            .apiInfo(
                new ApiInfo(
                    "Alfa-leasing Client Info REST API",
                    "Alfa-leasing REST API for handling client information",
                    "1.0",
                    null,
                    ApiInfo.DEFAULT_CONTACT,
                    null,
                    null,
                    Collections.emptyList())
            );
    }
}
