package ru.alfaleasing.dealer.offer.web.portal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Dto с невалидными данными о автомобиле (все поля - типа String)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExcelInvalidCar {
    /**
     * Производитель
     */
    private String brandName;

    /**
     * Имя модели
     */
    private String modelName;

    /**
     * Комплектация (equipmentName ?)
     */
    private String complectationName;

    /**
     * Год выпуска
     */
    private String manufactureYear;

    /**
     * Опции
     */
    private String extras;

    /**
     * Цвет кузова
     */
    private String color;

    /**
     * VIN
     */
    private String vin;

    /**
     * Конечная стоимость автомобиля
     */
    private String dealerPrice;

    /**
     * Наличие / поставка
     */
    private String availability;

    /**
     * Цвет обшивки салона
     */
    private String interiorColor;

    /**
     * Изображение / фото
     */
    private List<String> images;

    /**
     * Дата поставки а/м
     */
    private String deliveryDate;

    /**
     * Дополнительное оборудование ()
     */
    private List<Equipment> extraDealerEquipment;

    /**
     * РРЦ
     */
    private String msrp;

    /**
     * Размер скидки при покупке в лизинг
     */
    private String discount;

    /**
     * Местоположение авто
     */
    private String location;

    /**
     * Промо
     */
    private List<String> promo;

    /**
     * Онлайн бронирования
     */
    private String onlineReservation;

    /**
     * Коды дополнительных опций
     */
    private String extraOptions;

    /**
     * Код модели от производителя
     */
    private String modelCode;

    /**
     * Код комплектации от производителя
     */
    private String equipmentCode;

    /**
     * Код цвета кузова от производителя
     */
    private String colorCode;

    /**
     * Код цвета обшивки салона
     */
    private String interiorColorCode;
}