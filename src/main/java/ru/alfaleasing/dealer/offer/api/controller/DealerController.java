package ru.alfaleasing.dealer.offer.api.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alfaleasing.dealer.offer.api.dto.DealerDTO;

import java.time.LocalDateTime;

/**
 * Контроллер для работы с дилерами
 */
@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
@Slf4j
public class DealerController { // todo - перенести его в dealer-offer-web-portal

    @ApiOperation(value = "Метод для загрузки списка дилеров в БД шлюза")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "dealers are loaded successfully"),
        @ApiResponse(code = 401, message = " cannot load the dealer's list"),
    })
    @PostMapping("/load/dealers")
    public ResponseEntity<?> getOffersByApi(@ApiParam @RequestBody DealerDTO dealer) {
        log.info(LocalDateTime.now() + " request from postman to /v1/dealer/load");
        System.out.println("dealer's array from postman = " + dealer);
        // 1) Приходит контрагент из CRM (это фактически дилер)
        // сохраняем его в бд
        // 2) Создаём этому дилеру два типа конекшенов (API и FILE)
        //Примечание - конекшен для EXTERNAL_API создаётся в ручную а LINK - не используем вовсе (пока что)
        return ResponseEntity.ok().build();
    }
}
