package ru.alfaleasing.dealer.offer.web.portal.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

/**
 * Ответ с валидными и замапленными данными о автомобилях (на фронт)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class XmlSortedCarsResponse {

    /**
     * Ответ с невалидными автомобилями полученными их xml файла
     */
    List<XmlCarDTO> invalidCars;

    /**
     * Ответ с валидными автомобилями полученными их xml файла
     */
    List<XmlCarDTO> validCars;
}
