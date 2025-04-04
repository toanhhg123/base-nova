package com.example.demo.core.dto.response;

import com.example.demo.core.constants.ResponseStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Map;

@Setter
@Getter
@SuperBuilder
public class Response {
    private ResponseStatus status;
    private Integer statusCode;
    private String message;
    private String messCode;
    private String messSeq;
    private Object messParam;
    private Map<String, String> messLang;
}
