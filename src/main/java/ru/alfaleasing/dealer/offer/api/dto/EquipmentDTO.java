package ru.alfaleasing.dealer.offer.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@ApiModel(description = "Дополнительное оборудование которое устанавливает сам дилер")
public class EquipmentDTO {

    @ApiModelProperty(value = "Название дополнительного оборудования")
    String name;

    @ApiModelProperty(value = "Стоимость дополнительного оборудования")
    String price;
}
