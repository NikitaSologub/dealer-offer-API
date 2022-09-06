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
import ru.alfaleasing.dealer.offer.api.dto.Dealer;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * Контроллер для работы с дилерами
 */
@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
@Slf4j
public class DealerController {

    @ApiOperation(value = "Метод для загрузки списка дилеров в БД шлюза")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "dealers are loaded successfully"),
        @ApiResponse(code = 401, message = " cannot load the dealer's list"),
    })
    @PostMapping("/load/dealers")
    public ResponseEntity<?> getOffersByApi(@ApiParam @RequestBody Dealer[] dealer){
        log.debug(LocalDateTime.now() + " request from postman to /v1/dealer/load");
        System.out.println("dealer's array from postman = " + Arrays.toString(dealer));
        return ResponseEntity.ok().build();
    }
}
