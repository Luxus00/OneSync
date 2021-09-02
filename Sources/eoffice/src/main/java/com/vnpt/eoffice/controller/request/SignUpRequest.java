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
public class SignUpRequest {
    @NotBlank(message = Const.Message.Validation.FIRST_NAME_BLANK)
    private String firstName;

    @NotBlank(message = Const.Message.Validation.LAST_NAME_BLANK)
    private String lastName;

    @NotBlank(message ="Không được để trống username")
    private String username;

    @NotBlank(message = Const.Message.Validation.PASSWORD_BLANK)
    @Size(min = 8, max = 255, message = Const.Message.Validation.PASSWORD_SIZE)
    private String password;
}
