package ru.alfaleasing.dealer.offer.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Value;

import javax.annotation.Nullable;

@Value
@Builder
@ApiModel(description = "Дополнительное оборудование которое устанавливает сам дилер")
public class EquipmentDTO {

    @ApiModelProperty(value = "Название дополнительного оборудования")
    @JsonProperty("name")
    String name;

    @ApiModelProperty(value = "Стоимость дополнительного оборудования")
    @JsonProperty("price")
    @Nullable
    String price;

    @ApiModelProperty(value = "Количество единиц дополнительного оборудования")
    @JsonProperty("count")
    String count;
}
