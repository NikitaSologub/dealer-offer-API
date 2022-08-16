package ru.alfaleasing.dealer.offer.web.portal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Ответ с невалидными, валидными и замапленными данными о автомобилях (на фронт)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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