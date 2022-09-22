package ru.alfaleasing.dealer.offer.api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alfaleasing.dealer.offer.api.controller.swagger.StockApi;
import ru.alfaleasing.dealer.offer.api.dto.StockDTO;
import ru.alfaleasing.dealer.offer.api.service.CarService;

import java.util.List;
import java.util.UUID;

import static ru.alfaleasing.dealer.offer.api.constant.AppsConstant.AUTHORIZATION;
import static ru.alfaleasing.dealer.offer.api.constant.AppsConstant.X_CLIENT_ID;
import static ru.alfaleasing.dealer.offer.api.constant.AppsConstant.X_SALON_UID;
import static ru.alfaleasing.dealer.offer.api.constant.AppsConstant.X_SOURCE;

/**
 * Контроллер для загрузки стоков из dealer-offer-web-portal
 */
@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
@Slf4j
public class StockController implements StockApi {

    private final CarService carService;

    @PostMapping(value = "/offers-by-api/new")
    public ResponseEntity<UUID> loadStock(@RequestHeader(AUTHORIZATION) String token,
                                          @RequestHeader(X_SOURCE) String source,
                                          @RequestHeader(X_SALON_UID) UUID salonUid,
                                          @RequestHeader(X_CLIENT_ID) String clientId,
                                          @RequestBody List<StockDTO> stock) {
        log.info("Request with headers: clientId is {} source is {} clientId is {} salonUid is {}", clientId, source, clientId, salonUid);

        UUID taskUid = carService.loadStocksToMinioAndRabbit(stock, source, salonUid, clientId);
        return ResponseEntity
            .ok()
            .body(taskUid);
    }
}
