package org.example.utility.util;

import lombok.extern.slf4j.Slf4j;
import org.example.utility.constant.AppConstant;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@Slf4j
public class MessageResourceUtil implements MessageSourceAware {
    private static MessageSource messageSource;

    @Override
    public void setMessageSource(MessageSource messageSource) {
        MessageResourceUtil.messageSource = messageSource;
    }

    public static String getMessage(String code, Object... args) {
        try {

            return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
        } catch (NoSuchMessageException e) {
            log.warn("Message code " + code + " not found");
        } catch (Exception e) {
            log.warn("Get message resource error");
        }
        return code;
    }

    public static String getMessage(String code, String locale, Object... args) {
        try {
            if (AppConstant.LOCALE_EN.equals(locale)) {
                return messageSource.getMessage(code, args, Locale.ENGLISH);
            } else {
                // use ROOT for language VN
                return messageSource.getMessage(code, args, Locale.ROOT);
            }
        } catch (NoSuchMessageException e) {
            log.warn("Message code " + code + " not found");
        } catch (Exception e) {
            log.warn("Get message resource error");
        }
        return code;
    }
}
