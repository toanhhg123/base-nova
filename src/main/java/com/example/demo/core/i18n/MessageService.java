package com.example.demo.core.i18n;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class MessageService {
    private final RequestHeaderContext requestHeaderContext;
    private final MessageSource messageSource;


    public String getMessage(I18nMessage i18nMessage){
        return messageSource.getMessage(
                i18nMessage.getKey(),
                null,
                Locale.forLanguageTag(requestHeaderContext.getLang()));
    }
}