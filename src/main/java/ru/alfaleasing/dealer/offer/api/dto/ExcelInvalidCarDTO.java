//package ru.alfaleasing.dealer.offer.api.dto;
//
//import com.fasterxml.jackson.annotation.JsonProperty;
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
//@ApiModel(description = "Dto с невалидными данными о автомобиле (все поля - типа String)")
//public class ExcelInvalidCarDTO {
//
//    @ApiModelProperty(value = "Производитель", required = true)
//    String make;
//
//    @ApiModelProperty(value = "Имя модели", required = true)
//    String model;
//
//    @ApiModelProperty(value = "Комплектация", required = true)
//    String equipment;
//
//    @ApiModelProperty(value = "Год выпуска", required = true)
//    String manufactureYear;
//
//    @ApiModelProperty(value = "Опции", required = true)
//    private List<DescriptionDTO> descriptionComplectation;
//
//    @ApiModelProperty(value = "Цвет кузова", required = true)
//    String color;
//
//    @ApiModelProperty(value = "VIN", required = true)
//    String vin;
//
//    @ApiModelProperty(value = "Конечная стоимость автомобиля", required = true)
//    String price;
//
//    @ApiModelProperty(value = "Наличие / поставка", required = true)
//    String availability;
//
//    @ApiModelProperty(value = "Цвет обшивки салона")
//    String interiorColor;
//
//    @ApiModelProperty(value = "Изображение / фото")
//    List<String> images;
//
//    @ApiModelProperty(value = "Дата поставки а/м")
//    String deliveryDate;
//
//    @ApiModelProperty(value = "Дополнительное оборудование")
////    @JsonProperty("extraDealerEquipment")
//    List<EquipmentDTO> additionalEquipment;
//
//    @ApiModelProperty(value = "РРЦ")
//    String msrp;
//
//    @ApiModelProperty(value = "Размер скидки при покупке в лизинг")
//    String discount;
//
//    @ApiModelProperty(value = "Местоположение авто")
//    String location;
//
//    @ApiModelProperty(value = "Промо")
//    List<String> promo;
//
//    @ApiModelProperty(value = "Онлайн бронирования")
//    String onlineReservation;
//
//    @ApiModelProperty(value = "Коды дополнительных опций")
//    List<String> extraOptions;
//
//    @ApiModelProperty(value = "Код цвета кузова от производителя")
//    String colorCode;
//
//    @ApiModelProperty(value = "Код цвета обшивки салона")
//    String interiorColorCode;
//
//    @ApiModelProperty(value = "Код модели от производителя")
//    String modelCode;
//
//    @ApiModelProperty(value = "Код комплектации от производителя")
//    String equipmentCode;
//}
