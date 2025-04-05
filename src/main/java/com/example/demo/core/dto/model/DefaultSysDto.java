package com.example.demo.core.dto.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.UUID;


@FieldDefaults(level = AccessLevel.PROTECTED)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DefaultSysDto {
    UUID id;
    String noCode;
    String title;
    String titleEn;
    Integer sn;
    String status;
    UUID createdById;
    Date createdDate;
    UUID lastModifiedById;
    Date lastModifiedDate;
    Boolean isDelete;
    UUID deletedById;
    Date deletedDate;
    Boolean isParent;
    Integer lvl;
    UUID parentId;
    Integer subSn;
    String itemType;
    String description;
    String note;
}
