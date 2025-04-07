package com.example.demo.core.dto.body;

import com.example.demo.core.base.BaseRequestBodyDto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;
import java.util.Map;

/**
 * DTO for {@link com.example.demo.core.entities.SysMessage}
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysMessageRequestBodyDto extends BaseRequestBodyDto {
    @NotNull
    @NotBlank
    String messageSeq;

    @NotNull
    @NotBlank
    String messageCode;

    @NotNull
    @Min(100)
    Integer statusCode;

    @NotNull
    transient Map<String, String> translations = new HashMap<>();
}