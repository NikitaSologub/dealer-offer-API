package ru.alfaleasing.dealer.offer.web.portal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * Представление данных автомобиля из xml документа
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class XmlCar {
    /**
     * Производитель
     */
    private String brandName;

    /**
     * Имя модели
     */
    private String modelName;

    /**
     * Комплектация
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
     * Дополнительное оборудование
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
     * Промо (Описание скидки, подарочного предложения)
     */
    private List<String> promo;

    /**
     * Онлайн бронирование
     */
    private String onlineReservation;

    /**
     * Код цвета кузова от производителя
     */
    private String colorCode;

    /**
     * Код цвета обшивки салона
     */
    private String interiorColorCode;

    /**
     * Код модели от производителя
     */
    private String modelCode;

    /**
     * Код комплектации от производителя
     */
    private String equipmentCode;
}