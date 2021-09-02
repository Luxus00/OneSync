package com.vnpt.eoffice.config;

import com.vnpt.eoffice.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class Translator {

    private static ReloadableResourceBundleMessageSource messageSource;

    @Autowired
    Translator(ReloadableResourceBundleMessageSource messageSource) {
        Translator.messageSource = messageSource;
    }

    public static String translate(String msgCode) {
        Locale locale = LocaleContextHolder.getLocale();
        if (CommonUtils.isNullOrEmpty(msgCode)) return null;
        try {
            return messageSource.getMessage(msgCode, null, locale);
        } catch (NoSuchMessageException ex) {
            return msgCode;
        }

    }

    public static String translate(String msgCode, Object... data) {
        Locale locale = LocaleContextHolder.getLocale();
        if (CommonUtils.isNullOrEmpty(msgCode)) return null;
        try {
            return messageSource.getMessage(msgCode, data, locale);
        } catch (NoSuchMessageException ex) {
            return msgCode;
        }
    }

    public static String translate(String msgCode, String... data) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(msgCode, data, locale);
    }

    public static String getMessage(String key, String langCode, String... data) {
        langCode = CommonUtils.convertLangMessage(langCode);
        try {
            return messageSource.getMessage(key, data, Locale.forLanguageTag(langCode));
        } catch (Exception e) {
            return null;
        }
    }

    public static String getMessage(String key, String langCode) {
        langCode = CommonUtils.convertLangMessage(langCode);
        try {
            return messageSource.getMessage(key, null, Locale.forLanguageTag(langCode));
        } catch (Exception e) {
            return null;
        }
    }
}
