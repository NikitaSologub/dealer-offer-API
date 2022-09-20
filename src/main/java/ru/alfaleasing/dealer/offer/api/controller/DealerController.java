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
import ru.alfaleasing.dealer.offer.api.controller.swagger.DealerApi;
import ru.alfaleasing.dealer.offer.api.dto.DealerDTO;
import ru.alfaleasing.dealer.offer.api.dto.DealerInDbDTO;
import ru.alfaleasing.dealer.offer.api.service.DealerService;

import java.time.LocalDateTime;
import java.util.List;

import static ru.alfaleasing.dealer.offer.api.controller.StockController.AUTHORIZATION;
import static ru.alfaleasing.dealer.offer.api.controller.StockController.X_CLIENT_ID;

/**
 * Контроллер для работы с дилерами
 */
@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
@Slf4j
public class DealerController implements DealerApi {

    private final DealerService dealerService;

    @PostMapping("/load/dealer") // todo - добавить это для загрузки контрагентов
    public ResponseEntity<?> loadDealerFromCRM(@RequestHeader(AUTHORIZATION) String token,
                                               @RequestHeader(X_CLIENT_ID) String clientId,
                                               @ApiParam @RequestBody DealerDTO dealer) {
        log.info(LocalDateTime.now() + " request from postman to /v1/dealer/load");
        System.out.println("dealer's array from postman = " + dealer);
        // 1) Приходит контрагент из CRM (это фактически дилер)
        // сохраняем его в бд
        // 2) Создаём этому дилеру два типа конекшенов (API и FILE)
        //Примечание - конекшен для EXTERNAL_API создаётся в ручную а LINK - не используем вовсе (пока что)
        return ResponseEntity.ok().build();
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
