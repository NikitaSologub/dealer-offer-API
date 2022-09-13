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
@ApiModel(description = "Dto c валидной информацией о дилере")
public class DealerDTO {

    @ApiModelProperty(value = "Наименование дилера", required = true)
    String dealer;

    @ApiModelProperty(value = "Уникальный идентификатор дилера", required = true)
    String uid;

    @ApiModelProperty(value = "Регион")
    String region;

    @ApiModelProperty(value = "Город")
    String city;

    @ApiModelProperty(value = "Адрес", required = true)
    String address;

    @ApiModelProperty(value = "ИНН", required = true)
    String inn;

    @ApiModelProperty(value = "КПП", required = true)
    String kpp;
}
