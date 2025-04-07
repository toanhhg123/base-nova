package com.example.demo.core.dto.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder(builderMethodName = "of")
public class ResponsePagination<T> extends Response {
    private DataWithPagination<T> element;
}
