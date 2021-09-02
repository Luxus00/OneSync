package com.vnpt.eoffice.controller.listener.impl;

import com.vnpt.eoffice.controller.listener.HandlerListener;
import com.vnpt.eoffice.controller.request.SignUpRequest;
import com.vnpt.eoffice.dto.ChucVuDTO;
import com.vnpt.eoffice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class AddAccountHandlerListener extends HandlerListener<SignUpRequest> {
    @Autowired
    UserService userService;

    @Override
    protected void execute(SignUpRequest signUpRequest) {
        userService.registerUser(signUpRequest);
    }
}
