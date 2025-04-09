package com.example.demo.core.dto.body;

import com.example.demo.core.base.BaseEntityRequestDto;
import com.example.demo.core.constants.Validate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

/**
 * DTO for {@link com.example.demo.core.entities.SysMessage}
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysMessageCreateDto extends BaseEntityRequestDto {
    @NotNull
    @NotBlank
    String messageSeq;


    @NotBlank(message = Validate.NOT_BLANK)
    @NotNull(message = Validate.REQUIRED)
    String messageCode;

    @NotNull(message = Validate.REQUIRED)
    transient Map<String, String> translations;

    @NotNull(message = Validate.REQUIRED)
    Integer statusCode;
}