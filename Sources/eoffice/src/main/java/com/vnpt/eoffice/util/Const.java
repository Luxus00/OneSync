package com.vnpt.eoffice.util;

public interface Const {
    interface StatusCode {
        String SUCCESS = "0";
        String FAILURE = "1";
        String ERROR_VALIDATE = "2";
        String ACCESS_DENY = "4";
        String BAD_REQUEST = "5";
        String ERROR_SYSTEM = "6";
        String NOT_ACTIVE = "7";
        String IS_DISABLE = "8";
    }
    interface StatusUser {
        Integer NOT_ACTIVE = 0;
        Integer ACTIVE = 1;
        Integer DISABLE = 2;
    }

    interface Message {
        interface Common {
            String SUCCESS = "common.success";
            String FAILURE = "common.failure";
            String ERROR = "common.error";
            String ACCESS_DENY = "common.access_deny";
            String DISABLE = "common.disable";
            String BAD_REQUEST = "common.bad_request";
            String NOT_FOUND = "common.not_found";
            String ACCOUNT_DISABLE = "common.account_disable";
            String ACCOUNT_NOT_ACTIVE = "common.account.not_active";
        }
        interface Validation {
            String SUCCESS = "common.success";
            String FAILURE = "common.failure";
            String ERROR = "common.error";
            String ACCESS_DENY = "common.access_deny";
            String BAD_REQUEST = "common.bad_request";
            String NOT_FOUND = "common.not_found";
            /**
             * Sign up
             */
            // blank
            String ADDRESS_BLANK = "address.blank";
            String EMAIL_BLANK = "email.blank";
            String PASSWORD_BLANK = "password.blank";
            String PHONE_BLANK = "phone.blank";
            String USERNAME_BLANK = "username.blank";
            String FIRST_NAME_BLANK = "firstname.blank";
            String LAST_NAME_BLANK = "lastname.blank";
            String EMAIL_OR_PHONE_BLANK = "email_or_phone.blank";
            // size
            String ADDRESS_SIZE = "address.size";
            String PHONE_SIZE = "phone.size";
            String PASSWORD_SIZE="password.size";
            String CODE_BLANK="code.blank";
            String CODE_SIZE="code.size";

            // token
            String TOKEN_NOT_FOUND="token.notFound";
        }
    }
    interface Regex{
        String EMAIL="^([a-zA-Z0-9!#$%&'*+\\/=?^_`{|}~-]+(?:\\.[a-zA-Z0-9!#$%&'*+\\/=?^_`{|}~-]+)*@(?:[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?\\.)+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?)$";
        String PHONE_NUMBER="(84|0[3|1|5|7|8|9])+([0-9]{8})\\b";
    }
    interface FieldName{
        interface SignUp{
            String EMAIL_OR_PHONE="emailOrPhoneNumber";
            String COMMON="common";
        }
    }

    String CURRENT_SERVICE_NAME ="eoffice";
}
