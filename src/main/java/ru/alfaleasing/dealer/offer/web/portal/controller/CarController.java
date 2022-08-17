package ru.alfaleasing.dealer.offer.web.portal.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.alfaleasing.dealer.offer.web.portal.dto.ExcelSortedCarsResponse;
import ru.alfaleasing.dealer.offer.web.portal.dto.XmlSortedCarsResponse;
import ru.alfaleasing.dealer.offer.web.portal.service.CarService;

/**
 * Контроллер для получения списков валидных и невалидных автомобилей из приходящих файлов
 */
@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
@Slf4j
public class CarController {

    private final CarService carService;

    /**
     * Для извлечения автомобилей из xml файла и возвращения валидных и не валидных машин
     *
     * @param file файл с данными о автомобилях в формате xml
     * @return Запрос со списками валидных автомобилей
     */
    @PostMapping(value = "/dealer/xml")
    @ResponseBody
    public ResponseEntity<XmlSortedCarsResponse> getSortedResponseXml(@RequestParam("file") MultipartFile file) {
        return ResponseEntity
            .ok()
            .body(carService.getSortedCarsFromXml(file));
    }

    /**
     * Для извлечения автомобилей из xlsx файла и возвращения валидных и не валидных машин
     *
     * @param file файл с данными о автомобилях в формате xlsx
     * @return Запрос со списками валидных автомобилей
     */
    @PostMapping(value = "/dealer/xlsx")
    @ResponseBody
    public ResponseEntity<ExcelSortedCarsResponse> getSortedResponseXlsx(@RequestParam("file") MultipartFile file) {
        return ResponseEntity
            .ok()
            .body(carService.getSortedCarsFromXlsx(file));
    }

    /**
     * Для извлечения автомобилей из xls файла и возвращения валидных и не валидных машин
     *
     * @param file файл с данными о автомобилях в формате xls
     * @return Запрос со списками валидных автомобилей
     */
    @PostMapping(value = "/dealer/xls")
    @ResponseBody
    public ResponseEntity<ExcelSortedCarsResponse> getSortedResponseXls(@RequestParam("file") MultipartFile file) {
        return ResponseEntity
            .ok()
            .body(carService.getSortedCarsFromXls(file));
    }
}