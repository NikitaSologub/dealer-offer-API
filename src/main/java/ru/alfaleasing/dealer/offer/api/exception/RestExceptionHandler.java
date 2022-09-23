package ru.alfaleasing.dealer.offer.api.exception;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import io.minio.errors.MinioException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import java.security.AccessControlException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class RestExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<?> handleUnauthorizedException(HttpServletRequest request, AuthenticationException e) {
        return createResponseEntity(request, HttpStatus.UNAUTHORIZED, e);
    }

    @ExceptionHandler({AccessControlException.class, AccessDeniedException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<?> handleAccessControlException(HttpServletRequest request, RuntimeException e) {
        return createResponseEntity(request, HttpStatus.FORBIDDEN, e);
    }

    @ExceptionHandler({IllegalArgumentException.class, ConstraintViolationException.class,
            MissingRequestHeaderException.class, HttpMessageNotReadableException.class,
            MethodArgumentTypeMismatchException.class, MissingServletRequestParameterException.class,
            InvalidFormatException.class, JsonParseException.class, NoSuchElementException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleIllegalArgumentException(HttpServletRequest request, Exception e) {
        return createResponseEntity(request, HttpStatus.BAD_REQUEST, e);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleNotValidException(HttpServletRequest request, MethodArgumentNotValidException e) {
        List<String> errors = e.getBindingResult().getAllErrors().stream().map(error ->
                        String.format("%s%s", error instanceof FieldError ? (((FieldError) error).getField() + ": ") : "",
                                error.getDefaultMessage()))
                .collect(Collectors.toList());
        return createResponseEntity(request, HttpStatus.BAD_REQUEST, e, errors.toString());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(HttpServletRequest request, Exception e) {
        return createResponseEntity(request, HttpStatus.INTERNAL_SERVER_ERROR, e);
    }

    @ExceptionHandler(MinioException.class)
    public ResponseEntity<?> handleBucketNotExist(HttpServletRequest request, MinioException e) {
        log.error("Can't send dto to minIO. Bucket not exists. Error occurred: " + e);
        return createResponseEntity(request, HttpStatus.INTERNAL_SERVER_ERROR, e);
    }

    private ResponseEntity<?> createResponseEntity(HttpServletRequest request, HttpStatus status, Exception e) {
        log.warn(String.format("%s: %s", e.getClass().getTypeName(), e.getMessage()));
        return new ResponseEntity<>(new ErrorResponse(LocalDateTime.now(),
                status.value(), status.getReasonPhrase(),
                e.getMessage(), request.getRequestURI()),
                status);
    }

    private ResponseEntity<?> createResponseEntity(HttpServletRequest request, HttpStatus status, Exception e, String message) {
        log.warn(String.format("%s: %s - %s", e.getClass().getTypeName(), e.getMessage(), message));
        return new ResponseEntity<>(new ErrorResponse(LocalDateTime.now(),
                status.value(), status.getReasonPhrase(),
                message, request.getRequestURI()), status);
    }
}