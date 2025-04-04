package com.example.demo.core.entities;

import com.example.demo.core.constants.SysDatasourceNoCode;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "sys_histories")
public class SysHistory extends CoreEntity {
    @Column(name = "primary_table", nullable = false, length = Integer.MAX_VALUE)
    private String primaryTable;

    @Column(name = "trigger_type", nullable = false, length = Integer.MAX_VALUE)
    private String triggerType;

    @Column(name = "trigger_name", nullable = false, length = Integer.MAX_VALUE)
    private String triggerName;

    @Column(name = "trigger_function", nullable = false, length = Integer.MAX_VALUE)
    private String triggerFunction;

    @Column(name = "trigger_status", nullable = false, length = Integer.MAX_VALUE)
    private String triggerStatus;

    @Column(name = "hist_table", nullable = false, length = Integer.MAX_VALUE)
    private String histTable;

    @Column(name = "hist_schema", nullable = false, length = Integer.MAX_VALUE)
    private String histSchema;

    @Override
    public SysDatasourceNoCode getPrefixNoCode() {
        return SysDatasourceNoCode.DEFAULT;
    }
}