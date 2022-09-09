//package ru.alfaleasing.dealer.offer.api.dto;
//
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
//import lombok.AccessLevel;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.experimental.FieldDefaults;
//
//import java.util.List;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//@FieldDefaults(level = AccessLevel.PRIVATE)
//@ApiModel(description = "Ответ с невалидными, валидными и замапленными данными о автомобилях (на фронт)")
//public class XmlSortedCarsResponse {
//
//    @ApiModelProperty(value = "Ответ с невалидными автомобилями полученными их xml файла",required = true)
//    List<XmlCarDTO> invalidCars;
//
//    @ApiModelProperty(value = "Ответ с валидными автомобилями полученными их xml файла",required = true)
//    List<XmlCarDTO> validCars;
//}
