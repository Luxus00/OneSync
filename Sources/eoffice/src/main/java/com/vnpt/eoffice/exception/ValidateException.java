package com.vnpt.eoffice.exception;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class ValidateException extends RuntimeException {
    private Object data;
    private boolean isTranslate = true;
    private String fieldName;

    public ValidateException(String message) {
        super(message);
    }

    public ValidateException( String fieldName,String message) {
        super(message);
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public ValidateException(String message, boolean isTranslate, Object data) {
        super(message);
        this.isTranslate = isTranslate;
        this.data = data;
    }
}