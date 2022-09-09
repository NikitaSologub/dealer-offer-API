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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@ApiModel(description = "Дополнительное оборудование которое устанавливает сам дилер")
public class EquipmentDTO {

    @ApiModelProperty(value = "Название дополнительного оборудования")
    @JsonProperty("name")
    String name;

    @ApiModelProperty(value = "Стоимость дополнительного оборудования")
    @JsonProperty("price")
    String price;

    @ApiModelProperty(value = "Количество единиц дополнительного оборудования")
    @JsonProperty("count")
    private String count;
}
