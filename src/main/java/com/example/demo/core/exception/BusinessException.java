package com.example.demo.core.exception;

import com.example.demo.core.constants.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
@Builder
public class BusinessException extends RuntimeException {
    private final ResponseCode responseCode;
    private final transient Map<String, String> parameters;

    public BusinessException(ResponseCode responseCode) {
        this.responseCode = responseCode;
        this.parameters = null;
    }

    public static BusinessException of(ResponseCode statusMessage) {
        return new BusinessException(statusMessage);
    }

    public static BusinessException of(ResponseCode statusMessage, Map<String, String> parameters) {
        return new BusinessException(statusMessage, parameters);
    }

}
