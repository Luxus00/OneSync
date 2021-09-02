package com.vnpt.eoffice.service.impl;


import com.vnpt.eoffice.config.GenericMapper;
import com.vnpt.eoffice.controller.listener.ApiSynchronized;
import com.vnpt.eoffice.controller.listener.Message;
import com.vnpt.eoffice.controller.listener.PublicData;
import com.vnpt.eoffice.domain.User;
import com.vnpt.eoffice.domain.UserPrincipal;
import com.vnpt.eoffice.repository.UserRepository;
import com.vnpt.eoffice.security.AuthoritiesConstants;
import com.vnpt.eoffice.security.jwt.TokenProvider;
import com.vnpt.eoffice.service.UserService;
import com.vnpt.eoffice.util.CommonUtils;
import com.vnpt.eoffice.util.Const;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;

import com.vnpt.eoffice.controller.request.*;
import com.vnpt.eoffice.controller.response.*;

import com.vnpt.eoffice.exception.ValidateException;

@Service
@Slf4j
public class UserServiceImpl implements UserService {


    @Autowired
    UserRepository userRepository;


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private GenericMapper genericMapper;

    @Autowired
    TokenProvider tokenProvider;




    @Override
    @Transactional()
    public SignUpResponse registerUser(SignUpRequest signUpRequest) throws ValidateException {
        User user = new User();
        user.setFullName(signUpRequest.getFirstName() + " " + signUpRequest.getLastName());
        user.setPassword(bCryptPasswordEncoder.encode(signUpRequest.getPassword()));
        user.setRole(AuthoritiesConstants.CUSTOMER.getRole());
        user.setStatus(Const.StatusUser.ACTIVE);
        user.setUsername(signUpRequest.getUsername());
        userRepository.save(user);
        SignUpResponse signUpResponse = new SignUpResponse(signUpRequest.getUsername());
        return signUpResponse;
    }

    @Override
    public void resetPassword(ResetPasswordRequest resetPasswordRequest) throws ValidateException {
        userRepository.resetPassword(bCryptPasswordEncoder.encode(resetPasswordRequest.getPassword()),resetPasswordRequest.getUserName());
    }


    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public UserInfoResponse loginUser(LoginRequest loginRequest) {
        log.info("==> Login request" + loginRequest.toString());
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmailOrPhone(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.info("Logging in with [{}]", authentication.getPrincipal());
        var token = tokenProvider.createToken(authentication);
        var refreshToken = tokenProvider.createRefreshToken(authentication);
        List<GrantedAuthority> roles = new ArrayList<>(authentication.getAuthorities());
        String role = null;
        for (GrantedAuthority grantedAuthority : roles) {
            role = grantedAuthority.getAuthority();
        }
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
        return UserInfoResponse.builder()
                .accessToken(token)
                .refreshToken(refreshToken)
                .username(loginRequest.getEmailOrPhone())
                .role(role)
                .fullName(principal.getUser().getFullName())
                .build();
    }


    @Override
    public boolean isValidAccount(String userId) {
        log.info("===> USER_ID: " + userId);
        if (CommonUtils.isNullOrEmpty(userId)) {
            return false;
        } else {
            var optionalUser = userRepository.findByIdAndStatus(userId, Const.StatusUser.ACTIVE);
            return optionalUser.isPresent();
        }
    }


}
