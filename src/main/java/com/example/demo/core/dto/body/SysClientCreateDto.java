package com.example.demo.core.dto.body;

import com.example.demo.core.base.BaseEntityRequestDto;
import com.example.demo.core.constants.Validate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

import java.util.Map;

/**
 * DTO yêu cầu để tạo mới SysClient.
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = true)
@Data
public class SysClientCreateDto extends BaseEntityRequestDto {
    @NotBlank(message = Validate.NOT_BLANK)
    @NotNull(message = Validate.REQUIRED)
    String clientId;

    @NotBlank(message = Validate.NOT_BLANK)
    @NotNull(message = Validate.REQUIRED)
    String publicKey;

    String ipWhitelist;
    transient Map<String, Object> allowedEndpoints;
}