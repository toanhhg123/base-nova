package com.example.demo.core.entities;

import com.example.demo.core.base.BaseEntity;
import com.example.demo.core.constants.SysDatasourceNoCode;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "sys_filter_configs_roles")
public class SysFilterConfigsRole extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "resource_id", nullable = false)
    private SysResource resource;

    @Column(name = "field_name", nullable = false, length = 100)
    private String fieldName;

    @Column(name = "field_label", nullable = false, length = 100)
    private String fieldLabel;

    @Column(name = "filter_type_name", nullable = false, length = 100)
    private String filterTypeName;

    @Column(name = "data_type", nullable = false, length = 100)
    private String dataType;

    @ColumnDefault("false")
    @Column(name = "is_required", nullable = false)
    private Boolean isRequired = false;

    @ColumnDefault("false")
    @Column(name = "is_multiple", nullable = false)
    private Boolean isMultiple = false;

    @Column(name = "default_value", length = Integer.MAX_VALUE)
    private String defaultValue;

    @Column(name = "min_value", length = Integer.MAX_VALUE)
    private String minValue;

    @Column(name = "max_value", length = Integer.MAX_VALUE)
    private String maxValue;

    @Column(name = "validation_regex", length = Integer.MAX_VALUE)
    private String validationRegex;

    @ColumnDefault("0")
    @Column(name = "priority", nullable = false)
    private Integer priority;

    @ColumnDefault("false")
    @Column(name = "is_expired", nullable = false)
    private Boolean isExpired = false;

    @Column(name = "start_date")
    private Instant startDate;

    @Column(name = "end_date")
    private Instant endDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "role_id", nullable = false)
    private SysRole role;

    @Override
    public SysDatasourceNoCode getPrefixNoCode() {
        return SysDatasourceNoCode.DEFAULT;
    }
}