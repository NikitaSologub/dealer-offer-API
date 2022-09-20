package ru.alfaleasing.dealer.offer.api.controller.swagger;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import ru.alfaleasing.dealer.offer.api.dto.DealerDTO;

import java.util.List;

import static ru.alfaleasing.dealer.offer.api.controller.StockController.AUTHORIZATION;
import static ru.alfaleasing.dealer.offer.api.controller.StockController.X_CLIENT_ID;

public interface DealerApi {

    @ApiOperation(value = "Метод для загрузки списка дилеров в БД шлюза")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "dealers are loaded successfully"),
        @ApiResponse(code = 401, message = " cannot load the dealer's list"),
    })
    @PostMapping("/load/dealer")
    ResponseEntity<?> loadDealerFromCRM(@RequestHeader(AUTHORIZATION) String token,
                                        @RequestHeader(X_CLIENT_ID) String clientId,
                                        @ApiParam @RequestBody DealerDTO dealer);

    @ApiOperation(value = "Метод для получения списка контрагентов")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "dealers are received successfully"),
        @ApiResponse(code = 401, message = " cannot get list of dealers"),
    })
    @PostMapping("/dealers")
    ResponseEntity<List<DealerDTO>> getDealers(@RequestHeader(AUTHORIZATION) String token,
                                               @RequestHeader(X_CLIENT_ID) String clientId);
}
