package ru.alfaleasing.dealer.offer.api.controller;

import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alfaleasing.dealer.offer.api.swagger.DealerApi;
import ru.alfaleasing.dealer.offer.api.dto.DealerDTO;
import ru.alfaleasing.dealer.offer.api.dto.DealerInDbDTO;
import ru.alfaleasing.dealer.offer.api.service.DealerService;

import java.util.List;
import java.util.UUID;

import static ru.alfaleasing.dealer.offer.api.constant.AppsConstant.AUTHORIZATION;
import static ru.alfaleasing.dealer.offer.api.constant.AppsConstant.X_CLIENT_ID;

/**
 * Контроллер для работы с дилерами
 */
@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
@Slf4j
public class DealerController implements DealerApi {

    private final DealerService dealerService;

    @PostMapping("/load/dealer")
    public ResponseEntity<?> loadDealerFromCRM(@RequestHeader(AUTHORIZATION) String token,
                                               @RequestHeader(X_CLIENT_ID) String clientId,
                                               @ApiParam @RequestBody DealerDTO dealer) {
        log.info("Получили dealerDTO из 1С (CRM) = {}", dealer);
        UUID dealerUid = dealerService.loadDealer(dealer, clientId);
        return ResponseEntity
            .ok()
            .body(dealerUid);
    }

    @GetMapping("/dealers")
    public ResponseEntity<List<DealerInDbDTO>> getDealers(@RequestHeader(AUTHORIZATION) String token,
                                                          @RequestHeader(X_CLIENT_ID) String clientId) {
        List<DealerInDbDTO> dealers = dealerService.getAllDealers();
        log.info("Вернули список всех дилеров {}", dealers);
        return ResponseEntity
            .ok()
            .body(dealers);
    }
}
