package com.vnpt.eoffice.service;

import com.vnpt.eoffice.controller.request.*;
import com.vnpt.eoffice.controller.response.*;

import com.vnpt.eoffice.exception.ValidateException;


public interface UserService {
    SignUpResponse registerUser(SignUpRequest signUpRequest) throws ValidateException;

    void resetPassword(ResetPasswordRequest resetPasswordRequest) throws ValidateException;

    UserInfoResponse loginUser(LoginRequest loginRequest);

    boolean isValidAccount(String userId);

}
