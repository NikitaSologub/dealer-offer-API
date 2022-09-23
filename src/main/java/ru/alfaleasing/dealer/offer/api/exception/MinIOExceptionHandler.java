package ru.alfaleasing.dealer.offer.api.exception;

import io.minio.errors.MinioException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class MinIOExceptionHandler {

    @ExceptionHandler(MinioException.class)
    public void handleBucketNotExist(MinioException e) {
        log.error("Can't send dto to minIO. Bucket not exists. Error occurred: " + e);
        throw new RuntimeException(e);
    }
}
