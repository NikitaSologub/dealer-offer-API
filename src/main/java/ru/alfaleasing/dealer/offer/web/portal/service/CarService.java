package ru.alfaleasing.dealer.offer.web.portal.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.alfaleasing.dealer.offer.web.portal.client.DealerOfferWebPortalClient;
import ru.alfaleasing.dealer.offer.web.portal.dto.ExcelSortedCarsResponse;
import ru.alfaleasing.dealer.offer.web.portal.dto.XmlSortedCarsResponse;

/**
 * Сервис для работы с документами автомобилей
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CarService {

    private final DealerOfferWebPortalClient dealerOfferWebPortalClient;

    /**
     * Метод используется для извлечения автомобилей из xml файла и возвращения валидных и не валидных машин
     *
     * @param file файл с данными о автомобилях в формате xml
     * @return Запрос со списками валидных и не валидных автомобилей
     */
    public XmlSortedCarsResponse getSortedCarsFromXml(MultipartFile file) {
        return dealerOfferWebPortalClient.getSortedCarsFromXmlFile(file);
    }

    /**
     * Метод используется для извлечения автомобилей из xlsx файла и возвращения валидных и не валидных машин
     *
     * @param file файл с данными о автомобилях в формате xlsx
     * @return Запрос со списками валидных и не валидных автомобилей
     */
    public ExcelSortedCarsResponse getSortedCarsFromXlsx(MultipartFile file) {
        return dealerOfferWebPortalClient.getSortedCarsFromXlsxFile(file);
    }

    /**
     * Метод используется для извлечения автомобилей из xls файла и возвращения валидных и не валидных машин
     *
     * @param file файл с данными о автомобилях в формате xls
     * @return Запрос со списками валидных и не валидных автомобилей
     */
    public ExcelSortedCarsResponse getSortedCarsFromXls(MultipartFile file) {
        return dealerOfferWebPortalClient.getSortedCarsFromXlsFile(file);
    }
}