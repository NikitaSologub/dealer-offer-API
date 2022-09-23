package ru.alfaleasing.dealer.offer.api.exception;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ErrorResponse {

    LocalDateTime timestamp;
    int status;
    String error;
    String message;
    String path;
}
