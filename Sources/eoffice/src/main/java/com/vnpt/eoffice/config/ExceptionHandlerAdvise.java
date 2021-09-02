package com.vnpt.eoffice.config;


import com.vnpt.eoffice.exception.AccessDenyException;
import com.vnpt.eoffice.exception.ValidateException;
import com.vnpt.eoffice.util.Const;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.vnpt.eoffice.controller.response.ResponseBody;
import java.util.HashMap;

@Slf4j
@RestControllerAdvice
@ControllerAdvice
public class ExceptionHandlerAdvise extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public @org.springframework.web.bind.annotation.ResponseBody
    ResponseEntity<com.vnpt.eoffice.controller.response.ResponseBody> handleServerError(Exception ex, WebRequest request) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.ok(new ResponseBody()
                .setMessage(Translator.translate(Const.Message.Common.ERROR))
                .setCode(Const.StatusCode.ERROR_SYSTEM));
    }

    @ExceptionHandler(DisabledException.class)
    public @org.springframework.web.bind.annotation.ResponseBody
    ResponseEntity<ResponseBody> handleUserStatus(Exception ex, WebRequest request) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.ok(new ResponseBody()
                .setMessage(Translator.translate(Const.Message.Common.DISABLE))
                .setCode(Const.StatusCode.NOT_ACTIVE));
    }
    @ExceptionHandler(BadCredentialsException.class)
    public @org.springframework.web.bind.annotation.ResponseBody
    ResponseEntity<ResponseBody> handleBadCredentialsException(Exception ex, WebRequest request) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.ok(new ResponseBody()
                .setMessage(Translator.translate("Sai tài khoản hoặc mật khẩu"))
                .setCode(Const.StatusCode.ACCESS_DENY));
    }
    @ExceptionHandler(LockedException.class)
    public @org.springframework.web.bind.annotation.ResponseBody
    ResponseEntity<ResponseBody> handleLocked(Exception ex, WebRequest request) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.ok(new ResponseBody()
                .setMessage(Translator.translate(Const.Message.Common.ACCOUNT_DISABLE))
                .setCode(Const.StatusCode.IS_DISABLE));
    }

    @ExceptionHandler({
            SignatureException.class,
            MalformedJwtException.class,
            ExpiredJwtException.class,
            UnsupportedJwtException.class,
            IllegalArgumentException.class})
    public @org.springframework.web.bind.annotation.ResponseBody
    ResponseEntity<ResponseBody> handleAccessToken(Exception ex, WebRequest request) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.ok(new ResponseBody()
                .setMessage(Translator.translate(Const.Message.Common.DISABLE))
                .setCode(Const.StatusCode.ACCESS_DENY));
    }

    @ExceptionHandler(AccessDenyException.class)
    public @org.springframework.web.bind.annotation.ResponseBody
        ResponseEntity<ResponseBody> accessDenyException(Exception ex, WebRequest request) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.ok(new ResponseBody()
                .setMessage(Translator.translate(ex.getMessage()))
                .setCode(Const.StatusCode.ACCESS_DENY));
    }


    @ExceptionHandler(ValidateException.class)
    public @org.springframework.web.bind.annotation.ResponseBody
    ResponseEntity<ResponseBody> validateException(ValidateException ex, WebRequest request) {
        log.error(ex.getMessage(), ex);
        if (Strings.isBlank(ex.getFieldName())) {
            return ResponseEntity.ok(new ResponseBody()
                    .setMessage(ex.getMessage())
                    .setCode(Const.StatusCode.ERROR_VALIDATE));
        }
        HashMap<String, String> fieldErrors = new HashMap<>();
        fieldErrors.put(ex.getFieldName(), ex.getMessage());
        ResponseBody responseBodyDto = new ResponseBody();
        responseBodyDto.setMessage(Translator.translate(Const.Message.Common.FAILURE));
        responseBodyDto.setData(fieldErrors);
        responseBodyDto.setCode(Const.StatusCode.FAILURE);
        return ResponseEntity.ok(responseBodyDto);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseBody> badRequest(Exception ex, WebRequest request) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.ok(new ResponseBody()
                .setMessage(Translator.translate(Const.Message.Common.BAD_REQUEST))
                .setCode(Const.StatusCode.BAD_REQUEST));
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        BindingResult bindingResult = ex.getBindingResult();
        HashMap<String, String> fieldErrors = new HashMap<>();
        bindingResult.getFieldErrors().forEach(fieldError -> {
            fieldErrors.put(fieldError.getField(),fieldError.getDefaultMessage());
        });
        ResponseBody responseBodyDto = new ResponseBody();
        responseBodyDto.setMessage(Translator.translate(Const.Message.Common.FAILURE));
        responseBodyDto.setData(fieldErrors);
        responseBodyDto.setCode(Const.StatusCode.FAILURE);
        return ResponseEntity.ok(responseBodyDto);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.ok(new ResponseBody()
                .setMessage(Translator.translate(Const.Message.Common.BAD_REQUEST))
                .setCode(Const.StatusCode.BAD_REQUEST));
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.ok(new ResponseBody()
                .setMessage(Translator.translate(Const.Message.Common.NOT_FOUND))
                .setCode(Const.StatusCode.FAILURE));
    }
}
