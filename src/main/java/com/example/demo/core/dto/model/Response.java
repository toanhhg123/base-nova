package com.example.demo.core.dto.model;

import com.example.demo.core.constants.ResponseStatus;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.Map;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@SuperBuilder
public class Response {
    ResponseStatus status;
    Integer statusCode;
    String message;
    String messCode;
    String messSeq;
    Object messParam;
    Map<String, String> messLang;
}
