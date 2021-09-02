package com.vnpt.eoffice.exception;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AccessDenyException extends Exception {
    private Object data;
    private boolean isTranslate = true;

    public AccessDenyException(String message) {
        super(message);
    }

    public AccessDenyException(String message, boolean isTranslate, Object data) {
        super(message);
        this.isTranslate = isTranslate;
        this.data = data;
    }
}