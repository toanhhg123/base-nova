package com.example.demo.core.dto.body;

import com.example.demo.core.base.BaseDto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.Map;

/**
 * DTO for {@link com.example.demo.core.entities.SysMessage}
 */
@EqualsAndHashCode(callSuper = true)
@Value
public class SysMessageDto extends BaseDto {
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
    Map<String, String> translations;
}