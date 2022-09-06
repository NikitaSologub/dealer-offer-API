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
import ru.alfaleasing.dealer.offer.api.dto.ExcelSortedCarsResponse;
import ru.alfaleasing.dealer.offer.api.dto.XmlSortedCarsResponse;
import ru.alfaleasing.dealer.offer.api.service.CarService;

/**
 * Контроллер для получения списков валидных и невалидных автомобилей из приходящих файлов
 */
@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
@Slf4j
public class CarController {

    private final CarService carService;

    @ApiOperation(value = "Для извлечения автомобилей из xml файла и возвращения валидных и не валидных машин")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "car stock loaded successfully", response = XmlSortedCarsResponse.class),
        @ApiResponse(code = 401, message = "cannot load car stock"),
    })
    @PostMapping(value = "/dealer/xml")
    @ResponseBody
    public ResponseEntity<XmlSortedCarsResponse> getSortedResponseXml(@RequestParam("file") MultipartFile file) {
        return ResponseEntity
            .ok()
            .body(carService.getSortedCarsFromXml(file));
    }

    @ApiOperation(value = "Для извлечения автомобилей из xlsx файла и возвращения валидных и не валидных машин")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "car stock loaded successfully", response = ExcelSortedCarsResponse.class),
        @ApiResponse(code = 401, message = "cannot load car stock"),
    })
    @PostMapping(value = "/dealer/xlsx")
    @ResponseBody
    public ResponseEntity<ExcelSortedCarsResponse> getSortedResponseXlsx(@RequestParam("file") MultipartFile file) {
        return ResponseEntity
            .ok()
            .body(carService.getSortedCarsFromXlsx(file));
    }

    @ApiOperation(value = "Для извлечения автомобилей из xls файла и возвращения валидных и не валидных машин")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "car stock loaded successfully", response = ExcelSortedCarsResponse.class),
        @ApiResponse(code = 401, message = "cannot load car stock"),
    })
    @PostMapping(value = "/dealer/xls")
    @ResponseBody
    public ResponseEntity<ExcelSortedCarsResponse> getSortedResponseXls(@RequestParam("file") MultipartFile file) {
        return ResponseEntity
            .ok()
            .body(carService.getSortedCarsFromXls(file));
    }
}
