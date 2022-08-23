package ru.alfaleasing.dealer.offer.web.portal.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.alfaleasing.dealer.offer.web.portal.client.DealerOfferWebPortalClient;
import ru.alfaleasing.dealer.offer.web.portal.dto.ExcelSortedCarsResponse;
import ru.alfaleasing.dealer.offer.web.portal.dto.XmlSortedCarsResponse;
import ru.alfaleasing.dealer.offer.web.portal.queue.processor.QueueProcessor;

/**
 * Сервис для работы с документами автомобилей
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class CarService {

    private final DealerOfferWebPortalClient dealerOfferWebPortalClient;
    private final QueueProcessor queueProcessor;
    private final MinIOService minIOService;

    /**
     * Метод используется для извлечения автомобилей из xml файла и возвращения валидных и не валидных машин
     *
     * @param file файл с данными о автомобилях в формате xml
     * @return Запрос со списками валидных и не валидных автомобилей
     */
    public XmlSortedCarsResponse getSortedCarsFromXml(MultipartFile file) {
        System.out.println("Попали в сервис и пытаемся сходить на url: http://localhost:15072/dealer-offer-web-portal/v1/dealer/xml");
        log.debug("Попали в сервис и пытаемся сходить на url: http://localhost:15072/dealer-offer-web-portal/v1/dealer/xml");
        XmlSortedCarsResponse response = dealerOfferWebPortalClient.getSortedCarsFromXmlFile(file);
        queueProcessor.publishMessage(response.toString());
        minIOService.writeFileToMinIO(response);
        return response;
    }

    /**
     * Метод используется для извлечения автомобилей из xlsx файла и возвращения валидных и не валидных машин
     *
     * @param file файл с данными о автомобилях в формате xlsx
     * @return Запрос со списками валидных и не валидных автомобилей
     */
    public ExcelSortedCarsResponse getSortedCarsFromXlsx(MultipartFile file) {
        System.out.println("Попали в сервис и пытаемся сходить на url: http://localhost:15072/dealer-offer-web-portal/v1/dealer/xlsx");
        log.debug("Попали в сервис и пытаемся сходить на url: http://localhost:15072/dealer-offer-web-portal/v1/dealer/xlsx");
        ExcelSortedCarsResponse response = dealerOfferWebPortalClient.getSortedCarsFromXlsxFile(file);
        queueProcessor.publishMessage(response.toString());
        minIOService.writeFileToMinIO(response);
        return response;
    }

    /**
     * Метод используется для извлечения автомобилей из xls файла и возвращения валидных и не валидных машин
     *
     * @param file файл с данными о автомобилях в формате xls
     * @return Запрос со списками валидных и не валидных автомобилей
     */
    public ExcelSortedCarsResponse getSortedCarsFromXls(MultipartFile file) {
        System.out.println("Попали в сервис и пытаемся сходить на url: http://localhost:15072/dealer-offer-web-portal/v1/dealer/xls");
        log.debug("Попали в сервис и пытаемся сходить на url: http://localhost:15072/dealer-offer-web-portal/v1/dealer/xls");
        ExcelSortedCarsResponse response = dealerOfferWebPortalClient.getSortedCarsFromXlsFile(file);
        queueProcessor.publishMessage(response.toString());
        minIOService.writeFileToMinIO(response);
        return response;
    }
}