package com.vnpt.eoffice.security;


public enum AuthoritiesConstants {
    CUSTOMER("ROLE_CUSTOMER", 0), ANONYMOUS("ROLE_ANONYMOUS", 1),
    ADMIN("ROLE_ADMIN", 2);

    AuthoritiesConstants(String role, Integer code) {
        this.role = role;
        this.code = code;
    }

    private String role;
    private Integer code;

    public static AuthoritiesConstants fromCode(Integer code) {
        for (AuthoritiesConstants value : values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return null;
    }

    public String getRole() {
        return role;
    }

    public Integer getCode() {
        return code;
    }
}
