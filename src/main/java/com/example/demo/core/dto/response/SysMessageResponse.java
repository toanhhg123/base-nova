package com.example.demo.core.dto.response;

import com.example.demo.core.dto.model.DefaultSysDto;
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
public class SysMessageResponse extends DefaultSysDto {
    String messageSeq;
    String messageCode;
    Map<String, String> translations;
    Integer statusCode;
}