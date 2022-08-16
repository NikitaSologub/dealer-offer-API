package ru.alfaleasing.dealer.offer.web.portal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class ExcelCar {
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
    private BigInteger manufactureYear;

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
    private BigInteger dealerPrice;

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
    private LocalDate deliveryDate;

    /**
     * Дополнительное оборудование ()
     */
    private List<Equipment> extraDealerEquipment;

    /**
     * РРЦ
     */
    private BigInteger msrp;

    /**
     * Размер скидки при покупке в лизинг
     */
    private BigInteger discount;

    /**
     * Местоположение авто
     */
    private String location;

    /**
     * Промо
     */
    private List<String> promo;

    /**
     * Онлайн бронирование
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
    private BigInteger colorCode;

    /**
     * Код цвета обшивки салона
     */
    private String interiorColorCode;
}