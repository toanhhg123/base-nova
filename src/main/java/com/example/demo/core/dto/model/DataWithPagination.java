package com.example.demo.core.dto.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class DataWithPagination<T> {
    private long total;
    private int totalPage;
    private List<T> data;
}