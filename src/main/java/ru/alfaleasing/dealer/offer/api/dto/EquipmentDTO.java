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

import javax.annotation.Nullable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@ApiModel(description = "Дополнительное оборудование которое устанавливает сам дилер")
public class EquipmentDTO {

    @ApiModelProperty(value = "Название дополнительного оборудования")
    @JsonProperty("equipment_name")
    String equipmentName;

    @ApiModelProperty(value = "Стоимость дополнительного оборудования")
    @JsonProperty("equipment_price")
    @Nullable
    String equipmentPrice;

    @ApiModelProperty(value = "Количество единиц дополнительного оборудования")
    @JsonProperty("equipment_count")
    private String equipmentCount;
}
