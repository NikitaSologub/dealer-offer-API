package ru.alfaleasing.dealer.offer.api.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alfaleasing.dealer.offer.api.service.MinIOService;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
@Slf4j
public class DealerOfferController {

    private final MinIOService minIOService;

    @ApiOperation(value = "Метод для приёмки стока из автомира")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "dealers are loaded successfully"),
        @ApiResponse(code = 401, message = "cannot load the dealer's list"),
    })
    @PostMapping("/offers-by-api/new")
    public void getOffersByApi(@RequestBody Object offer){
        log.info(LocalDateTime.now() + " request from avtomir-dealer-provider");
        minIOService.writeFileToMinIO(offer);
    }
}
