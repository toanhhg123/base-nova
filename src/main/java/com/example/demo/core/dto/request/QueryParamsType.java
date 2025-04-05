package com.example.demo.core.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Map;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class QueryParamsType {
    Integer limit = 20;
    String search = "";
    Integer page = 1;
    Long startTime = 0L;
    Long endTime = 0L;
    Map<String, Object> additionalParams;
}