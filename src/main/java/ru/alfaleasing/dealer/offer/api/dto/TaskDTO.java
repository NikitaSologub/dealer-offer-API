package ru.alfaleasing.dealer.offer.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
@ApiModel(description = "Dto таски для отправки в Rabbit")
public class TaskDTO {

    @JsonProperty("task_uid")
    UUID taskUid;

    @JsonProperty("dealer_uid")
    UUID dealerUid;

    @JsonProperty("dealer_name")
    String dealerName;

    @JsonProperty("city")
    String city;

    @JsonProperty("is_used")
    boolean isUsed;

    // название это UUID таски + .json
    @JsonProperty("s3_object_name")
    String s3ObjectName;
}
