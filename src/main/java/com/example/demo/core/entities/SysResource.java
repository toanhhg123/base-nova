package com.example.demo.core.entities;

import com.example.demo.core.base.BaseEntity;
import com.example.demo.core.constants.SysDatasourceNoCode;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@NoArgsConstructor
@SuperBuilder
@Table(name = "sys_resources")
public class SysResource extends BaseEntity {
    @Column(name = "resource")
    private String resource;

    @Column(name = "scope")
    private String scope;

    @Column(name = "i18n")
    private String i18n;

    @ColumnDefault("false")
    @Column(name = "is_group", nullable = false)
    private Boolean isGroup = false;

    @ColumnDefault("false")
    @Column(name = "hide_in_breadcrumb", nullable = false)
    private Boolean hideInBreadcrumb = false;

    @Column(name = "icon")
    private String icon;

    @Column(name = "slug")
    private String slug;

    @Column(name = "module_id")
    private String moduleId;

    @Column(name = "module_type")
    private String moduleType;

    @Column(name = "group_type")
    private String groupType;

    @Override
    public SysDatasourceNoCode getPrefixNoCode() {
        return SysDatasourceNoCode.SYS_RESOURCE;
    }

}