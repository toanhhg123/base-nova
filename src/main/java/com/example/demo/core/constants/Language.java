package com.example.demo.core.constants;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Language {
    VI("vi"),
    EN("en"),
    LANG_HEADER("lang");

    public final String value;

    public static Language fromValue(String value) {
        for (Language lang : Language.values()) {
            if (lang.value.equals(value)) {
                return lang;
            }
        }
        return Language.VI;
    }
}
