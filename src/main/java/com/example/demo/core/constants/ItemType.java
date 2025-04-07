package com.example.demo.core.constants;

import lombok.Getter;

@Getter
public enum ItemType {
    MENU("menu"),
    ;

    private final String value;

    ItemType(String value) {
        this.value = value;
    }

}
