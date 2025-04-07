package com.example.demo.core.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class QueryParams {
    private static final String DEFAULT_SORT = "createdDate";
    Integer limit = 20;
    String search = "";
    Integer page = 1;
    Instant startTime;
    Instant endTime;
    Map<String, Object> additionalParams = new HashMap<>();

    public Pageable toPageable() {
        Sort sort = Sort.by(DEFAULT_SORT).descending();

        var currentPage = getCurrentPage();
        return PageRequest.of(currentPage, limit, sort);
    }

    public Integer getCurrentPage() {
        return page - 1;
    }


}