package ru.alfaleasing.dealer.offer.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@ApiModel(description = "Dto c валидной информацией о контрагенте")
public class DealerInDbDTO {

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

    @ApiModelProperty(value = "Дата занесения контрагента в базу")
    String createDate;

    @ApiModelProperty(value = "Автор занесения контрагента в базу")
    String createAuthor;

    @ApiModelProperty(value = "Последняя дата внесения данных по любому Connection для этого контрагента")
    String lastUpdated;
}
