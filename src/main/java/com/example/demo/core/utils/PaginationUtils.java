package com.example.demo.core.utils;

import com.example.demo.core.dto.model.DataWithPagination;
import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Page;

import java.util.function.Function;

/**
 * Utility class for pagination operations
 */
@UtilityClass
public class PaginationUtils {

    /**
     * Maps a Spring Page object to a DataWithPagination object
     *
     * @param page   The Spring Page object containing the source data
     * @param mapper Function to map from source type S to target type T
     * @param <S>    Source entity type
     * @param <T>    Target DTO type
     * @return DataWithPagination object with mapped content
     */
    public <S, T> DataWithPagination<T> mapPageToDataWithPagination(Page<S> page, Function<S, T> mapper) {
        return DataWithPagination.<T>builder()
                .total(page.getTotalElements())
                .totalPage(page.getTotalPages())
                .data(page.getContent().stream().map(mapper).toList())
                .build();
    }
}
