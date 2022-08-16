package ru.alfaleasing.dealer.offer.web.portal.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.alfaleasing.dealer.offer.web.portal.dto.XmlSortedCarsResponse;

/**
 * Сервис для работы с документами автомобилей
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CarService {

    /**
     * Метод используется для извлечения автомобилей из xml файла и возвращения валидных и не валидных машин
     *
     * @param file файл с данными о автомобилях в формате xml
     * @return Запрос со списками валидных и не валидных автомобилей
     */
    public XmlSortedCarsResponse getSortedCarsFromXml(MultipartFile file) {
        return new XmlSortedCarsResponse();
    }
}