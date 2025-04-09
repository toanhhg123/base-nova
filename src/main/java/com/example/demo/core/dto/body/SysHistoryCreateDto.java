package com.example.demo.core.dto.body;

import com.example.demo.core.base.BaseEntityRequestDto;
import com.example.demo.core.constants.Validate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DTO for {@link com.example.demo.core.entities.SysHistory}
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysHistoryCreateDto extends BaseEntityRequestDto {
    @NotNull(message = Validate.REQUIRED)
    @NotBlank(message = Validate.NOT_BLANK)
    String primaryTable;

    @NotNull(message = Validate.REQUIRED)
    @NotBlank(message = Validate.NOT_BLANK)
    String triggerType;

    @NotNull(message = Validate.REQUIRED)
    @NotBlank(message = Validate.NOT_BLANK)
    String triggerName;

    @NotNull(message = Validate.REQUIRED)
    @NotBlank(message = Validate.NOT_BLANK)
    String triggerFunction;

    @NotNull(message = Validate.REQUIRED)
    @NotBlank(message = Validate.NOT_BLANK)
    String triggerStatus;

    @NotNull(message = Validate.REQUIRED)
    @NotBlank(message = Validate.NOT_BLANK)
    String histTable;
    
    @NotNull(message = Validate.REQUIRED)
    @NotBlank(message = Validate.NOT_BLANK)
    String histSchema;
}