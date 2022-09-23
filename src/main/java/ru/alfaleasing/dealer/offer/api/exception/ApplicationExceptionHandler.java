package ru.alfaleasing.dealer.offer.api.exception;

import com.fasterxml.jackson.core.JsonParseException;
import io.minio.errors.MinioException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ApplicationExceptionHandler {

    @ExceptionHandler(JsonParseException.class)
    public ResponseEntity<?> handleConflict(JsonParseException e) {
        log.info("Не удалось разобрать тело запроса из Json в объект TaskResponseFromCSharpSystemDTO ={}", e.getMessage());
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(MinioException.class)
    public ResponseEntity<?> handleBucketNotExist(MinioException e) {
        log.error("Can't send dto to minIO. Bucket not exists. Error occurred: " + e);
        return ResponseEntity.badRequest().build();
    }
}
