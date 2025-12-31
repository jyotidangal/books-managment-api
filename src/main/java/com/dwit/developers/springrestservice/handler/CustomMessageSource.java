package com.dwit.developers.springrestservice.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;


import java.util.Locale;


@Component
@RequiredArgsConstructor
public class CustomMessageSource {


    private final MessageSource messageSource;


    public String get(String code, Object... objects) {
        return messageSource.getMessage(code, objects, getCurrentLocale());
    }


    public Locale getCurrentLocale() {
        Locale locale = LocaleContextHolder.getLocale();
        if (locale.getDisplayLanguage().equalsIgnoreCase("np")) {
            locale = new Locale("np", "Nepal");
        } else {
            return locale;
        }
        return locale;
    }
}
