package com.example.demo.core.constants;

import lombok.Getter;

@Getter
public enum ResponseStatus {
    SUCCESS("success"),
    ERROR("error");

    private final String value;

    ResponseStatus(String value) {
        this.value = value;
    }

}
