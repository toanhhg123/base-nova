package com.example.demo.core.dto.response;

import com.example.demo.core.base.BaseEntityDto;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

import java.util.Map;

/**
 * DTO for {@link com.example.demo.core.entities.SysMessage}
 */

@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = true)
@Data
public class SysMessageDto extends BaseEntityDto {
    String messageSeq;
    String messageCode;
    transient Map<String, String> translations;
    Integer statusCode;
}