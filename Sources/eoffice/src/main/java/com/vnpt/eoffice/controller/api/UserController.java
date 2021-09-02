package com.vnpt.eoffice.controller.api;


import com.vnpt.eoffice.config.log.LogAble;
import com.vnpt.eoffice.controller.listener.ApiSynchronized;
import com.vnpt.eoffice.controller.listener.Message;
import com.vnpt.eoffice.controller.listener.PublicData;
import com.vnpt.eoffice.controller.request.*;
import com.vnpt.eoffice.controller.response.*;
import com.vnpt.eoffice.controller.response.ResponseBody;
import com.vnpt.eoffice.exception.ValidateException;
import com.vnpt.eoffice.service.UserService;
import com.vnpt.eoffice.util.Const;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;


@RestController
@Slf4j
@RequestMapping("auth")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    PublicData publicData;

    @PostMapping("login")
    @LogAble
    public ResponseEntity<ResponseBody> login(@Valid @RequestBody LoginRequest loginRequest) {
        var userInfoResponse = userService.loginUser(loginRequest);
        return ResponseEntity.ok(ResponseBody.ofSuccess(userInfoResponse));
    }

//    @GetMapping("refresh-token")
//    @LogAble
//    public ResponseEntity<ResponseBody> getRefreshToken() {
//        var refreshTokenResponse = userService.getRefreshToken();
//        return ResponseEntity.ok(ResponseBody.ofSuccess(refreshTokenResponse));
//    }

    @PostMapping("register")
    @LogAble
    public ResponseEntity<ResponseBody> register(@Valid @RequestBody SignUpRequest signUpRequest) throws ValidateException {
        SignUpResponse signUpResponse = userService.registerUser(signUpRequest);
        publicData.postForObject(new Message(ApiSynchronized.ADD_ACCOUNT.name(), Const.CURRENT_SERVICE_NAME,signUpRequest));
        return ResponseEntity.ok(ResponseBody.ofSuccess(signUpResponse));
    }


    @PostMapping("reset-password")
    @LogAble
    public ResponseEntity<ResponseBody> resetPasswordRequest(@Valid @RequestBody ResetPasswordRequest resetPasswordRequest) throws ValidateException {
        userService.resetPassword(resetPasswordRequest);
        publicData.postForObject(new Message(
                ApiSynchronized.RESET_PASS.name(),
                Const.CURRENT_SERVICE_NAME,
                resetPasswordRequest
        ));
        return ResponseEntity.ok(ResponseBody.ofSuccess());
    }





}
