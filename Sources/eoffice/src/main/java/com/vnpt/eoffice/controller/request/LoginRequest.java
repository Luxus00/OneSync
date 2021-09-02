package com.vnpt.eoffice.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LoginRequest implements Serializable {
    @NotBlank(message = "Vui lòng nhập số  điện thoại hoặc email")
    private String emailOrPhone;
    @NotBlank(message = "Bạn chưa nhập mật khẩu")
    private String password;
}
