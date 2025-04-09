package com.example.demo.core.base;

import com.example.demo.core.constants.EntityStatus;
import com.example.demo.core.constants.Validate;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.UUID;

@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode
@Data
public class BaseEntityRequestDto implements Serializable {
    @JsonIgnore
    protected UUID userRequestId;
    @Size(max = 255)
    String title;
    @Size(max = 255)
    String titleEn;
    Integer sn;
    @NotNull
    @Size(max = 20)
    String status = EntityStatus.ACTIVE.getValue();
    Boolean isParent;
    @NotNull(message = Validate.REQUIRED)
    Integer lvl;
    String parentId;
    Integer subSn;
    @Size(max = 50)
    String itemType;
    String description;
    String note;
}
