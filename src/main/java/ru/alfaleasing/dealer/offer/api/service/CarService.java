package ru.alfaleasing.dealer.offer.api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
//import ru.alfaleasing.dealer.offer.api.dto.ExcelSortedCarsResponse;
import ru.alfaleasing.dealer.offer.api.client.DealerOfferWebPortalClient;
import ru.alfaleasing.dealer.offer.api.dto.ExcelCarDTO;
import ru.alfaleasing.dealer.offer.api.dto.XmlCarDTO;
//import ru.alfaleasing.dealer.offer.api.dto.XmlSortedCarsResponse;
import ru.alfaleasing.dealer.offer.api.queue.processor.QueueProcessor;

import java.util.List;

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

    @Value("${client.dealer-offer-web-portal.url}")
    private String url;

    /**
     * Метод используется для извлечения автомобилей из xml файла и возвращения валидных и не валидных машин
     *
     * @param file файл с данными о автомобилях в формате xml
     * @return Запрос со списками валидных и не валидных автомобилей
     */
    public List<XmlCarDTO> getSortedCarsFromXml(MultipartFile file) {
        log.info("Попали в сервис и пытаемся сходить на url: {}/v1/dealer/xml", url);
        List<XmlCarDTO> stock = dealerOfferWebPortalClient.getSortedCarsFromXmlFile(file);
        queueProcessor.publishMessage(stock);
        minIOService.writeFileToMinIO(stock);
        return stock;
    }

    /**
     * Метод используется для извлечения автомобилей из xlsx файла и возвращения валидных и не валидных машин
     *
     * @param file файл с данными о автомобилях в формате xlsx
     * @return Запрос со списками валидных и не валидных автомобилей
     */
    public List<ExcelCarDTO> getSortedCarsFromXlsx(MultipartFile file) {
        log.info("Попали в сервис и пытаемся сходить на url: {}/v1/dealer/xlsx", url);
        List<ExcelCarDTO> stock = dealerOfferWebPortalClient.getSortedCarsFromXlsxFile(file);
        queueProcessor.publishMessage(stock);
        minIOService.writeFileToMinIO(stock);
        return stock;
    }

    /**
     * Метод используется для извлечения автомобилей из xls файла и возвращения валидных и не валидных машин
     *
     * @param file файл с данными о автомобилях в формате xls
     * @return Запрос со списками валидных и не валидных автомобилей
     */
    public List<ExcelCarDTO> getSortedCarsFromXls(MultipartFile file) {
        log.info("Попали в сервис и пытаемся сходить на url: {}/v1/dealer/xls", url);
        List<ExcelCarDTO> stock = dealerOfferWebPortalClient.getSortedCarsFromXlsFile(file);
        queueProcessor.publishMessage(stock);
        minIOService.writeFileToMinIO(stock);
        return stock;
    }
}
