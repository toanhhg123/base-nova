package com.example.demo.core.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Map;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
public class QueryParamsType {
    Integer limit;
    String search;
    Integer page;
    Long startTime;
    Long endTime;
    Map<String, Object> additionalParams;
}