package ru.alfaleasing.dealer.offer.web.portal.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

/**
 * Dto с невалидными данными о автомобиле (все поля - типа String)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExcelInvalidCar {
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
     * Дополнительное оборудование ()
     */
    List<Equipment> extraDealerEquipment;

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
     * Промо
     */
    List<String> promo;

    /**
     * Онлайн бронирования
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
    String colorCode;

    /**
     * Код цвета обшивки салона
     */
    String interiorColorCode;
}