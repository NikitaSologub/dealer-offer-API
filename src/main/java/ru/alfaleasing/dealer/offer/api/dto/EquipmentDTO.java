package ru.alfaleasing.dealer.offer.api.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

/**
 * Дополнительное оборудование которое устанавливает сам дилер (может влиять на цену авто в сторону увеличения)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EquipmentDTO {
    /**
     * Название дополнительного оборудования
     */
    String name;

    /**
     * Стоимость дополнительного оборудования
     */
    String price;
}
