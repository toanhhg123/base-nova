package com.example.demo.core.base;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

/**
 * DTO for {@link BaseEntity}
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@EqualsAndHashCode
public class BaseEntityDto implements Serializable {
    UUID id;
    @Size(max = 50)
    String noCode;
    @Size(max = 255)
    String title;
    @Size(max = 255)
    String titleEn;
    Integer sn;
    @NotNull
    @Size(max = 20)
    String status;
    UUID createdById;
    @NotNull
    Instant createdDate;
    UUID lastModifiedById;
    @NotNull
    Instant lastModifiedDate;
    @NotNull
    Boolean isDelete;
    String deletedById;
    Instant deletedDate;
    Boolean isParent;
    @NotNull
    Integer lvl;
    String parentId;
    Integer subSn;
    @Size(max = 50)
    String itemType;
    String description;
    String note;
}