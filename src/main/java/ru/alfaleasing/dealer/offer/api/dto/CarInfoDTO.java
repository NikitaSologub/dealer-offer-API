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
@ApiModel(description = "Информация о стоке при прохождении систем ГОИ и КЛИ")
public class CarInfoDTO {

    @ApiModelProperty(value = "vin")
    @JsonProperty("vin")
    String vin;

    @ApiModelProperty(value = "В каком состоянии находится проверка стока после прохождения ГОИ или КЛИ")
    @JsonProperty("status")
    String status;

    @ApiModelProperty(value = "Описание причины публикации (или не публикации) конкретного стока")
    @JsonProperty("description")
    String description;
}
