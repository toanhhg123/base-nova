package com.example.demo.core.dto.response;

import com.example.demo.core.base.BaseEntityDto;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

/**
 * DTO for {@link com.example.demo.core.entities.SysUserRole}
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = true)
@Data
public class SysUserRoleResponse extends BaseEntityDto {
    Integer priority;
    Boolean isExpired;
    Instant startDate;
    Instant endDate;
    Integer roleType;
}