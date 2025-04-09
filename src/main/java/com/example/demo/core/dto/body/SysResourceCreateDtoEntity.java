package com.example.demo.core.dto.body;

import com.example.demo.core.base.BaseEntityRequestDto;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = true)
@Data
public class SysResourceCreateDtoEntity extends BaseEntityRequestDto {
    String resource;
    String scope;

    @NotNull
    String i18n;

    Boolean isGroup;
    Boolean hideInBreadcrumb;
    String icon;
    String slug;
    String moduleId;
    String moduleType;
    String groupType;
}
