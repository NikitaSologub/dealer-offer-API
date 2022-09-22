package ru.alfaleasing.dealer.offer.api.controller.swagger;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import ru.alfaleasing.dealer.offer.api.dto.StockDTO;

import java.util.List;
import java.util.UUID;

import static ru.alfaleasing.dealer.offer.api.constant.AppsConstant.AUTHORIZATION;
import static ru.alfaleasing.dealer.offer.api.constant.AppsConstant.X_CLIENT_ID;
import static ru.alfaleasing.dealer.offer.api.constant.AppsConstant.X_SALON_UID;
import static ru.alfaleasing.dealer.offer.api.constant.AppsConstant.X_SOURCE;

public interface StockApi {

    @ApiOperation(value = "Для загрузки стоков")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "car stock loaded successfully", response = StockDTO.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "cannot load car stock"),
    })
    @PostMapping(value = "/offers-by-api/new")
    ResponseEntity<UUID> loadStock(@RequestHeader(AUTHORIZATION) String token,
                                   @RequestHeader(value = X_SOURCE, required = false) String source,
                                   @RequestHeader(X_SALON_UID) UUID salonUid,
                                   @RequestHeader(X_CLIENT_ID) String clientId,
                                   @RequestBody List<StockDTO> stock);
}
