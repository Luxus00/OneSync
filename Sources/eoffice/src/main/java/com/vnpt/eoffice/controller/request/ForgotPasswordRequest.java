package com.vnpt.eoffice.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import com.vnpt.eoffice.util.Const;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString
public class ForgotPasswordRequest {
    @NotBlank(message = Const.Message.Validation.EMAIL_OR_PHONE_BLANK)
    private String emailOrPhoneNumber;
}
