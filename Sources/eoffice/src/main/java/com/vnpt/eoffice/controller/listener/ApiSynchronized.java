package com.vnpt.eoffice.controller.listener;

import com.vnpt.eoffice.controller.listener.impl.AddChucVuHandlerListener;

import java.lang.reflect.Type;

public enum ApiSynchronized {
    ADD_ACCOUNT(null),
    RESET_PASS(null),
    CHUC_VU_CREATE(AddChucVuHandlerListener.class),
    CHUC_VU_DELETE(null),
    CHUC_VU_UPDATE(null),
    DON_VI_CHUC_NANG_CREATE(null),
    DON_VI_CHUC_NANG_DELETE(null),
    DON_VI_CHUC_NANG_UPDATE(null),
    ;
    Type classHandler;

    ApiSynchronized(Type classHandler) {
        this.classHandler = classHandler;
    }

    public Type getClassHandler() {
        return classHandler;
    }

    public void setClassHandler(Type classHandler) {
        this.classHandler = classHandler;
    }
}
