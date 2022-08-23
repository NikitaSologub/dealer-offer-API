package ru.alfaleasing.dealer.offer.web.portal.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

/**
 * Ответ с невалидными, валидными и замапленными данными о автомобилях (на фронт)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExcelSortedCarsResponse {
    /**
     * Список Dto c невалидной информацией о автомобилях
     */
    List<ExcelInvalidCar> invalidCars;

    /**
     * Список Dto c валидной информацией о автомобилях
     */
    List<ExcelCar> validCars;
}