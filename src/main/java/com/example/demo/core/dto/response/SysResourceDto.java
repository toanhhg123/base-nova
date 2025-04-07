package com.example.demo.core.dto.response;

import com.example.demo.core.base.BaseEntityDto;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

/**
 * DTO for {@link com.example.demo.core.entities.SysResource}
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = true)
@Data
public class SysResourceDto extends BaseEntityDto {
    String resource;
    String scope;
    String i18n;
    Boolean isGroup;
    Boolean hideInBreadcrumb;
    String icon;
    String slug;
    String moduleId;
    String moduleType;
    String groupType;
}