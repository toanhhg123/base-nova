package com.example.demo.core.constants;

import lombok.Getter;

@Getter
public enum EntityStatus {
    ACTIVE("Active"),
    INACTIVE("InActive");

    private final String value;

    EntityStatus(String value) {
        this.value = value;
    }
}
