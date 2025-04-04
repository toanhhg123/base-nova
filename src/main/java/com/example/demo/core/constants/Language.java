package com.example.demo.core.constants;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public enum Language {
    VI("vi"),
    EN("en"),
    LANG_HEADER("lang");

    public final String value;
}
