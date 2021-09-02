package com.vnpt.eoffice.controller.request;

import com.vnpt.eoffice.util.Const;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString
public class ConfirmCodeForgotPasswordRequest {

    @NotBlank(message = Const.Message.Validation.EMAIL_OR_PHONE_BLANK)
    private String emailOrPhoneNumber;

    @NotBlank(message = Const.Message.Validation.CODE_BLANK)
    @Size(min = 6,max = 6, message = Const.Message.Validation.CODE_SIZE)
    private String code;

}
