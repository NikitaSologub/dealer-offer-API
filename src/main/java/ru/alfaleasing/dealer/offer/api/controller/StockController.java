package ru.alfaleasing.dealer.offer.api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alfaleasing.dealer.offer.api.controller.param.LoadingType;
import ru.alfaleasing.dealer.offer.api.controller.swagger.StockApi;
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
public class StockController implements StockApi {

    public static final String AUTHORIZATION = "Authorization";
    public static final String X_SOURCE = "X-SOURCE";
    public static final String X_SALON_UID = "X-SALON-UID";
    public static final String X_CLIENT_ID = "X-CLIENT-ID";
    private final CarService carService;

    @PostMapping(value = "/offers-by-api/new")
    public ResponseEntity<UUID> loadStock(@RequestHeader(AUTHORIZATION) String token,
                                          @RequestHeader(X_SOURCE) String source,
                                          @RequestHeader(X_SALON_UID) UUID salonUid, // 3fa85f64-5717-4562-b3fc-2c963f66afa6
                                          @RequestHeader(X_CLIENT_ID) String clientId,
                                          @RequestBody List<StockDTO> stock) {

        log.info("Request with headers: clientId is {} source is {} clientId is {} salonUid is {}", clientId, source, clientId, salonUid);

        if (LoadingType.FILE.name().equals(source)) {
            log.info("Начинаем загрузку стоков по файлу из dealer-offer-web-portal");
        } else if (LoadingType.API.name().equals(source)) {
            log.info("Начинаем загрузку стоков из Automir");
        } else {
            throw new IllegalArgumentException("X_SOURCE не ни FILE ни API");
        }
        return ResponseEntity
            .ok()
            .body(carService.loadStocksToMinioAndRabbit(stock, source, salonUid, clientId));
    }
}
