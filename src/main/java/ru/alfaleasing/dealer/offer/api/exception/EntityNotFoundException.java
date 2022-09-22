package ru.alfaleasing.dealer.offer.api.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntityNotFoundException extends RuntimeException {

    private String entityName;
    private String errorFieldName;
    private Object errorFieldValue;

    public EntityNotFoundException(String entityName, String errorFieldName, Object errorFieldValue) {
        super(String.format("%s is not found with %s = %s", entityName, errorFieldName, errorFieldValue));
        this.entityName = entityName;
        this.errorFieldName = errorFieldName;
        this.errorFieldValue = errorFieldValue;
    }
}
