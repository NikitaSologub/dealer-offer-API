package ru.alfaleasing.dealer.offer.web.portal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

/**
 * Dto c валидной информацией о автомобиле
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExcelCarDTO {
    /**
     * Производитель
     */
    String brandName;

    /**
     * Имя модели
     */
    String modelName;

    /**
     * Комплектация (equipmentName ?)
     */
    String complectationName;

    /**
     * Год выпуска
     */
    BigInteger manufactureYear;

    /**
     * Опции
     */
    String extras;

    /**
     * Цвет кузова
     */
    String color;

    /**
     * VIN
     */
    String vin;

    /**
     * Конечная стоимость автомобиля
     */
    BigInteger dealerPrice;

    /**
     * Наличие / поставка
     */
    String availability;

    /**
     * Цвет обшивки салона
     */
    String interiorColor;

    /**
     * Изображение / фото
     */
    List<String> images;

    /**
     * Дата поставки а/м
     */
    LocalDate deliveryDate;

    /**
     * Дополнительное оборудование ()
     */
    @JsonProperty("extraDealerEquipment")
    List<EquipmentDTO> extraDealerEquipmentDTO;

    /**
     * РРЦ
     */
    BigInteger msrp;

    /**
     * Размер скидки при покупке в лизинг
     */
    BigInteger discount;

    /**
     * Местоположение авто
     */
    String location;

    /**
     * Промо
     */
    List<String> promo;

    /**
     * Онлайн бронирование
     */
    String onlineReservation;

    /**
     * Коды дополнительных опций
     */
    String extraOptions;

    /**
     * Код модели от производителя
     */
    String modelCode;

    /**
     * Код комплектации от производителя
     */
    String equipmentCode;

    /**
     * Код цвета кузова от производителя
     */
    BigInteger colorCode;

    /**
     * Код цвета обшивки салона
     */
    String interiorColorCode;
}
