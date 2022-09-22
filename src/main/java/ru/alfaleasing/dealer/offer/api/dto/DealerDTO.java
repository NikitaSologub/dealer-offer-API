package ru.alfaleasing.dealer.offer.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(description = "Dto c валидной информацией о контрагенте")
public class DealerDTO {

    @ApiModelProperty(value = "Уникальный идентификатор дилера", required = true)
    String uid;

    @ApiModelProperty(value = "Наименование дилера", required = true)
    String dealer;

    @ApiModelProperty(value = "ИНН (идентификационный номер налогоплательщика)", required = true)
    String inn;

    @ApiModelProperty(value = "КПП (код причины постановки)", required = true)
    String kpp;

    @ApiModelProperty(value = "Код региона", required = true)
    String region;

    @ApiModelProperty(value = "Город")
    String city;

    @ApiModelProperty(value = "Адрес - в базе данных - location")
    String address;
}
