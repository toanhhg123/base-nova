package com.example.demo.core.dto.response;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder(builderMethodName = "responseDataBuilder")
public class ResponseData<T> extends Response {
    private T element;
}