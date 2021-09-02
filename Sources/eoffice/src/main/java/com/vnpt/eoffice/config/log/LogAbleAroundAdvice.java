package com.vnpt.eoffice.config.log;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Class write log request and response for api
 */
@Component
@Aspect
@Slf4j
public class LogAbleAroundAdvice {
    @Around("within(com.vnpt.eoffice.controller.api.*) && @annotation(com.vnpt.eoffice.config.log.LogAble)")
    public Object writeLog(ProceedingJoinPoint joinPoint) throws Throwable {
        // Lấy danh sách tham số
        StringBuilder requestBuilder = new StringBuilder();
        for (Object arg : joinPoint.getArgs()) {
            requestBuilder.append(arg).append(Strings.LINE_SEPARATOR);
        }

        // Lấy tên method thực thi
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        log.debug(String.format(" Method: %s /n request %s",method,requestBuilder.toString()));

        // Thực hiện gọi chạy hàm và lấy kết quả tả về
        Object resultAfterProcess = joinPoint.proceed();
        log.debug(String.format("Method: %s /n response %s",method,resultAfterProcess.toString()));
        return resultAfterProcess;
    }
}
