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
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@ApiModel(description = "Dto c валидной информацией о автомобиле")
public class ExcelCarDTO {

    @ApiModelProperty(value = "Производитель", required = true)
    String brandName;

    @ApiModelProperty(value = "Имя модели", required = true)
    String modelName;

    @ApiModelProperty(value = "Комплектация", required = true)
    String complectationName;

    @ApiModelProperty(value = "Год выпуска", required = true)
    BigInteger manufactureYear;

    @ApiModelProperty(value = "Опции", required = true)
    String extras;

    @ApiModelProperty(value = "Цвет кузова", required = true)
    String color;

    @ApiModelProperty(value = "VIN", required = true)
    String vin;

    @ApiModelProperty(value = "Конечная стоимость автомобиля", required = true)
    BigInteger dealerPrice;

    @ApiModelProperty(value = "Наличие / поставка", required = true)
    String availability;

    @ApiModelProperty(value = "Цвет обшивки салона")
    String interiorColor;

    @ApiModelProperty(value = "Изображение / фото")
    List<String> images;

    @ApiModelProperty(value = "Дата поставки а/м")
    LocalDate deliveryDate;

    @ApiModelProperty(value = "Дополнительное оборудование")
    @JsonProperty("extraDealerEquipment")
    List<EquipmentDTO> extraDealerEquipmentDTO;

    @ApiModelProperty(value = "РРЦ")
    BigInteger msrp;

    @ApiModelProperty(value = "Размер скидки при покупке в лизинг")
    BigInteger discount;

    @ApiModelProperty(value = "Местоположение авто")
    String location;

    @ApiModelProperty(value = "Промо")
    List<String> promo;

    @ApiModelProperty(value = "Онлайн бронирование")
    String onlineReservation;

    @ApiModelProperty(value = "Коды дополнительных опций")
    String extraOptions;

    @ApiModelProperty(value = "Код модели от производителя")
    String modelCode;

    @ApiModelProperty(value = "Код комплектации от производителя")
    String equipmentCode;

    @ApiModelProperty(value = "Код цвета кузова от производителя")
    BigInteger colorCode;

    @ApiModelProperty(value = "Код цвета обшивки салона")
    String interiorColorCode;
}
