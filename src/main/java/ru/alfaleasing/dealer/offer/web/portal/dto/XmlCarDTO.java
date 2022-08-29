package ru.alfaleasing.dealer.offer.web.portal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

/**
 * Представление данных автомобиля из xml документа
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class XmlCarDTO {
    /**
     * Производитель
     */
    String brandName;

    /**
     * Имя модели
     */
    String modelName;

    /**
     * Комплектация
     */
    String complectationName;

    /**
     * Год выпуска
     */
    String manufactureYear;

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
    String dealerPrice;

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
    String deliveryDate;

    /**
     * Дополнительное оборудование
     */
    @JsonProperty("extraDealerEquipment")
    List<EquipmentDTO> extraDealerEquipmentDTO;

    /**
     * РРЦ
     */
    String msrp;

    /**
     * Размер скидки при покупке в лизинг
     */
    String discount;

    /**
     * Местоположение авто
     */
    String location;

    /**
     * Промо (Описание скидки, подарочного предложения)
     */
    List<String> promo;

    /**
     * Онлайн бронирование
     */
    String onlineReservation;

    /**
     * Код цвета кузова от производителя
     */
    String colorCode;

    /**
     * Код цвета обшивки салона
     */
    String interiorColorCode;

    /**
     * Код модели от производителя
     */
    String modelCode;

    /**
     * Код комплектации от производителя
     */
    String equipmentCode;
}
