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
public class ResetPasswordRequest {
    String userName;
    @NotBlank(message = Const.Message.Validation.PASSWORD_BLANK)
    @Size(min = 8, max = 255, message = Const.Message.Validation.PASSWORD_SIZE)
    String password;
}
