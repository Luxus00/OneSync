package com.vnpt.eoffice.exception;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class ResourceViolationException extends Exception {
    private Object data;
    private boolean isTranslate = true;

    public ResourceViolationException(String message) {
        super(message);
    }

    public ResourceViolationException(String message, boolean isTranslate, Object data) {
        super(message);
        this.isTranslate = isTranslate;
        this.data = data;
    }
}