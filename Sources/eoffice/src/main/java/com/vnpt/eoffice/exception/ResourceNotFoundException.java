package com.vnpt.eoffice.exception;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class ResourceNotFoundException extends Exception {
    private Object data;
    private boolean isTranslate = true;

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, boolean isTranslate, Object data) {
        super(message);
        this.isTranslate = isTranslate;
        this.data = data;
    }
}