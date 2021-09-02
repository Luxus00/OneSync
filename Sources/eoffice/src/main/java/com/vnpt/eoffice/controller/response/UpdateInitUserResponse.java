package com.vnpt.eoffice.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UpdateInitUserResponse {
    private String role;

    private String username;

    private String avatar;

    private String fullName;

}
