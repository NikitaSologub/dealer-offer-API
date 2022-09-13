package ru.alfaleasing.dealer.offer.api.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.alfaleasing.dealer.offer.api.dto.StockDTO;
import ru.alfaleasing.dealer.offer.api.service.CarService;

import java.util.List;

/**
 * Контроллер для получения списков валидных стоков из dealer-offer-web-portal
 */
@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
@Slf4j
public class StockController {

    private final CarService carService;

    @ApiOperation(value = "Для извлечения автомобилей из xlsx файла и возвращения валидных и не валидных машин")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "car stock loaded successfully", response = StockDTO.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "cannot load car stock"),
    })
    @PostMapping(value = "/dealer/xlsx")
    @ResponseBody
    public ResponseEntity<List<StockDTO>> getSortedResponseXlsx(@RequestParam("file") MultipartFile file) {
        return ResponseEntity
            .ok()
            .body(carService.getSortedCarsFromXlsx(file));
    }

    @ApiOperation(value = "Для извлечения автомобилей из xls файла и возвращения валидных и не валидных машин")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "car stock loaded successfully", response = StockDTO.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "cannot load car stock"),
    })
    @PostMapping(value = "/dealer/xls")
    @ResponseBody
    public ResponseEntity<List<StockDTO>> getSortedResponseXls(@RequestParam("file") MultipartFile file) {
        return ResponseEntity
            .ok()
            .body(carService.getSortedCarsFromXls(file));
    }
}
