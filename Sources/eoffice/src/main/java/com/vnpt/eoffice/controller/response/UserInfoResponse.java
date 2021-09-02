package com.vnpt.eoffice.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserInfoResponse implements Serializable {
    private String username;
    private String fullName;
    private String refreshToken;
    private String accessToken;
    private String role;
}
