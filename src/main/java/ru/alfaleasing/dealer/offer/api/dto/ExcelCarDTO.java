package ru.alfaleasing.dealer.offer.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigInteger;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@ApiModel(description = "Dto c валидной информацией о автомобиле")
public class ExcelCarDTO {

    @ApiModelProperty(value = "Производитель", required = true)
    @JsonProperty("make")
    String make;

    @ApiModelProperty(value = "Имя модели", required = true)
    @JsonProperty("model")
    String model;

    @ApiModelProperty(value = "Комплектация", required = true)
    @JsonProperty("equipment")
    String equipment;

    @ApiModelProperty(value = "Год выпуска", required = true)
    @JsonProperty("manufacture_year")
    BigInteger manufactureYear;

    @ApiModelProperty(value = "Опции", required = true)
    @JsonProperty("description_complectation")
    List<DescriptionDTO> descriptionComplectation;

    @ApiModelProperty(value = "Цвет кузова", required = true)
    @JsonProperty("color")
    String color;

    @ApiModelProperty(value = "VIN", required = true)
    @JsonProperty("vin")
    String vin;

    @ApiModelProperty(value = "Конечная стоимость автомобиля", required = true)
    @JsonProperty("price")
    BigInteger price;

    @ApiModelProperty(value = "Наличие / поставка", required = true)
    @JsonProperty("availability")
    String availability;

    @ApiModelProperty(value = "Цвет обшивки салона")
    @JsonProperty("interior_color")
    String interiorColor;

    @ApiModelProperty(value = "Изображение / фото")
    @JsonProperty("images")
    List<String> images;

    @ApiModelProperty(value = "Дата поставки а/м")
    @JsonProperty("delivery_date")
    String deliveryDate;

    @ApiModelProperty(value = "Дополнительное оборудование")
    @JsonProperty("additional_equipment")
    List<EquipmentDTO> additionalEquipment;

    @ApiModelProperty(value = "РРЦ")
    @JsonProperty("msrp")
    BigInteger msrp;

    @ApiModelProperty(value = "Размер скидки при покупке в лизинг")
    @JsonProperty("discount")
    BigInteger discount;

    @ApiModelProperty(value = "Местоположение авто")
    @JsonProperty("location")
    String location;

    @ApiModelProperty(value = "Промо")
    @JsonProperty("promo")
    List<String> promo;

    @ApiModelProperty(value = "Онлайн бронирование")
    @JsonProperty("online_reservation")
    String onlineReservation;

    @ApiModelProperty(value = "Коды дополнительных опций")
    @JsonProperty("extra_options")
    List<String> extraOptions;

    @ApiModelProperty(value = "Код цвета кузова от производителя")
    @JsonProperty("color_code")
    BigInteger colorCode;

    @ApiModelProperty(value = "Код цвета обшивки салона")
    @JsonProperty("interior_color_code")
    String interiorColorCode;

    @ApiModelProperty(value = "Код модели от производителя")
    @JsonProperty("model_code")
    String modelCode;

    @ApiModelProperty(value = "Код комплектации от производителя")
    @JsonProperty("equipment_code")
    String equipmentCode;
}
