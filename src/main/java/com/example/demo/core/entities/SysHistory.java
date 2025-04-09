package com.example.demo.core.entities;

import com.example.demo.core.base.BaseEntity;
import com.example.demo.core.constants.SysDatasourceNoCode;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "sys_histories")
public class SysHistory extends BaseEntity {
    @NotNull
    @Column(name = "primary_table", nullable = false, length = Integer.MAX_VALUE)
    private String primaryTable;

    @NotNull
    @Column(name = "trigger_type", nullable = false, length = Integer.MAX_VALUE)
    private String triggerType;

    @NotNull
    @Column(name = "trigger_name", nullable = false, length = Integer.MAX_VALUE)
    private String triggerName;

    @NotNull
    @Column(name = "trigger_function", nullable = false, length = Integer.MAX_VALUE)
    private String triggerFunction;

    @NotNull
    @Column(name = "trigger_status", nullable = false, length = Integer.MAX_VALUE)
    private String triggerStatus;

    @NotNull
    @Column(name = "hist_table", nullable = false, length = Integer.MAX_VALUE)
    private String histTable;

    @NotNull
    @Column(name = "hist_schema", nullable = false, length = Integer.MAX_VALUE)
    private String histSchema;

    @Override
    public SysDatasourceNoCode getPrefixNoCode() {
        return SysDatasourceNoCode.DEFAULT;
    }
}