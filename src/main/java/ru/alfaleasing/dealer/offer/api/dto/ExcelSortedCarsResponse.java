package ru.alfaleasing.dealer.offer.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@ApiModel(description = "Ответ с невалидными, валидными и замапленными данными о автомобилях (на фронт)")
public class ExcelSortedCarsResponse {

    @ApiModelProperty(value = "Список Dto c невалидной информацией о автомобилях", required = true)
    List<ExcelInvalidCarDTO> invalidCars;

    @ApiModelProperty(value = "Список Dto c валидной информацией о автомобилях", required = true)
    List<ExcelCarDTO> validCars;
}
