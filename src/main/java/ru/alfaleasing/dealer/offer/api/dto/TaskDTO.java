package ru.alfaleasing.dealer.offer.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
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

    @JsonProperty("s3_object_name")
    String s3ObjectName;
}
