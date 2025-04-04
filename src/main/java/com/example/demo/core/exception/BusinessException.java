package com.example.demo.core.exception;

import com.example.demo.core.constants.HttpStatusMessage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
@Builder
public class BusinessException  extends RuntimeException{
    private final HttpStatusMessage statusMessage;
    private final transient Map<String, String> parameters;

    public BusinessException(HttpStatusMessage statusMessage) {
        this.statusMessage = statusMessage;
        this.parameters = null;
    }

    public static BusinessException of(HttpStatusMessage statusMessage) {
        return new BusinessException(statusMessage);
    }

    public static BusinessException of(HttpStatusMessage statusMessage, Map<String, String> parameters) {
        return new BusinessException(statusMessage, parameters);
    }

}
