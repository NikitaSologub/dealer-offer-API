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
import ru.alfaleasing.dealer.offer.api.constant.TaskStatus;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@ApiModel(description = "Информация о таске при прохождении систем ГОИ и КЛИ со всеми стоками")
public class TaskResultDTO {

    @ApiModelProperty(value = "UUID задачи по проверке стоков в системах ГОИ и КЛИ")
    @JsonProperty("task_uid")
    UUID taskUid;

    @ApiModelProperty(value = "Статус задачи про прохождении систем ГОИ и КЛИ")
    @JsonProperty("status")
    TaskStatus status;

    @ApiModelProperty(value = "Список автомобилей с их конкретными статусами после прохождения систем ГОИ и КЛИ")
    @JsonProperty("results")
    List<CarInfoDTO> results;
}
