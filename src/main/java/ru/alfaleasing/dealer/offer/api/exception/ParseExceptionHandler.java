package ru.alfaleasing.dealer.offer.api.exception;

import com.fasterxml.jackson.core.JsonParseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ParseExceptionHandler {

    @ExceptionHandler(JsonParseException.class)
    public void handleConflict(JsonParseException e) {
        log.info("Не удалось разобрать тело запроса из Json в объект TaskResponseFromCSharpSystemDTO ={}", e.getMessage());
    }
}

