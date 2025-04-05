package com.example.demo.core.base;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

public class BaseDto {
    @JsonIgnore
    protected UUID userRequestId;
}
