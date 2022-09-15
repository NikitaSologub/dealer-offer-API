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
import ru.alfaleasing.dealer.offer.api.controller.param.Type;
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

    public static final String AUTHORIZATION = "Authorization";
    public static final String X_METHOD_TYPE = "X-METHOD-TYPE";
    public static final String X_SALON_UID = "X-SALON-UID";
    public static final String X_CLIENT_ID = "X-CLIENT-ID";
    private final CarService carService;

    @ApiOperation(value = "Для загрузки стоков")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "car stock loaded successfully", response = StockDTO.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "cannot load car stock"),
    })
    @PostMapping(value = "/offers-by-api/new/")
    @ResponseBody
    public ResponseEntity<?> loadStock(@RequestHeader(AUTHORIZATION) String authorization,
                                       @RequestHeader(X_METHOD_TYPE) String methodType, // FILE
                                       @RequestHeader(X_SALON_UID) UUID salonUid, // 3fa85f64-5717-4562-b3fc-2c963f66afa6
                                       @RequestHeader(X_CLIENT_ID) String clientId, // dschemeris - OAuth2
                                       @RequestBody List<StockDTO> stock) {

        if (methodType == null || Type.API.name().equals(methodType)) {
            log.info("Начинаем загрузку стоков по API из Automir");
        } else if (Type.FILE.name().equals(methodType)) {
            log.info("Начинаем загрузку стоков по файлу из dealer-offer-web-portal");
        }
        carService.loadStocksToMinioAndRabbit(stock, methodType, salonUid);

        return ResponseEntity
            .ok().build();
    }
}
