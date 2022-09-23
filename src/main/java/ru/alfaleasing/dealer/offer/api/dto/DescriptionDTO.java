package ru.alfaleasing.dealer.offer.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(description = "Описание комплектации")
public class DescriptionDTO {

    @ApiModelProperty(value = "Название категории дополнительного оборудования")
    @JsonProperty("category_name")
    String categoryName;

    @ApiModelProperty(value = "Стоимость дополнительного оборудования")
    @JsonProperty("category_options")
    List<String> categoryOptions;
}