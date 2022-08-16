package ru.alfaleasing.dealer.offer.web.portal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Дополнительное оборудование которое устанавливает сам дилер (может влиять на цену авто в сторону увеличения)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Equipment {
    /**
     * Название дополнительного оборудования
     */
    private String name;

    /**
     * Стоимость дополнительного оборудования
     */
    private String price;
}