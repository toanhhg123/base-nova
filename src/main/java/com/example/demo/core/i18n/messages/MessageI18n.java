package com.example.demo.core.i18n.messages;

import com.example.demo.core.i18n.I18nMessage;

public enum MessageI18n implements I18nMessage {
    HELLO("message.hello"),
    ;

    final String key;

    MessageI18n(String key) {
        this.key = key;
    }

    @Override
    public String getKey() {
        return this.key;
    }

}
