package ru.alfaleasing.dealer.offer.web.portal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Ответ с валидными и замапленными данными о автомобилях (на фронт)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class XmlSortedCarsResponse {

    /**
     * Ответ с невалидными автомобилями полученными их xml файла
     */
    private List<XmlCar> invalidCars;

    /**
     * Ответ с валидными автомобилями полученными их xml файла
     */
    private List<XmlCar> validCars;
}