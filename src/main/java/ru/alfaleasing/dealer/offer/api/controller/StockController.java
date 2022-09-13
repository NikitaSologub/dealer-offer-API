package ru.alfaleasing.dealer.offer.api.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.alfaleasing.dealer.offer.api.dto.StockDTO;
import ru.alfaleasing.dealer.offer.api.service.CarService;

import java.util.List;
import java.util.UUID;

/**
 * Контроллер для загрузки стоков из dealer-offer-web-portal
 */
@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
@Slf4j
public class StockController {

    private final CarService carService;

    @ApiOperation(value = "Для загрузки стоков")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "car stock loaded successfully", response = StockDTO.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "cannot load car stock"),
    })
    @PostMapping(value = "/offers-by-api/new/")
    @ResponseBody
    public ResponseEntity<?> loadStock(@RequestHeader("Authorization") String authorization,
                                       @RequestHeader("X-METHOD-TYPE") String methodType, // FILE
                                       @RequestHeader("X-SALON-UID") UUID salonUid, // 3fa85f64-5717-4562-b3fc-2c963f66afa6
                                       @RequestHeader("X-CLIENT-ID") String clientId, // dschemeris - OAuth2
                                       @RequestBody List<StockDTO> stock) {
        return ResponseEntity
            .ok()
            .body(carService.loadStocksToMinioAndRabbit(stock, methodType, salonUid));
    }

    @ApiOperation(value = "Для автомобилей из xls файла и возвращения валидных и не валидных машин")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "car stock loaded successfully", response = StockDTO.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "cannot load car stock"),
    })
    @PostMapping(value = "/offers-by-api/new2/")
    @ResponseBody
    public ResponseEntity<?> loadStock2(@RequestHeader("Authorization") String authorization,
                                        @RequestHeader("X-METHOD-TYPE") String methodType, // FILE
                                        @RequestHeader("X-SALON-UID") UUID salonUid, // 3fa85f64-5717-4562-b3fc-2c963f66afa6
                                        @RequestHeader("X-CLIENT-ID") String clientId, // dschemeris - OAuth2
                                        @RequestBody List<StockDTO> stock) {
        System.out.println("Вы попали по адресу v1/offers-by-api/new2/");
        System.out.println("Authorization " + authorization);
        return ResponseEntity
            .ok().build();
    }
}
