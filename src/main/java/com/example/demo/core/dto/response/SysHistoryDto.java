package com.example.demo.core.dto.response;

import com.example.demo.core.base.BaseEntityDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DTO for {@link com.example.demo.core.entities.SysHistory}
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysHistoryDto extends BaseEntityDto {
    String primaryTable;
    String triggerType;
    String triggerName;
    String triggerFunction;
    String triggerStatus;
    String histTable;
    String histSchema;
}