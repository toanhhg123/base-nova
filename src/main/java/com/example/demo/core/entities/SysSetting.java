package com.example.demo.core.entities;

import com.example.demo.core.base.BaseEntity;
import com.example.demo.core.constants.SysDatasourceNoCode;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Map;

@Setter
@Getter
@Entity
@Table(name = "sys_settings")
public class SysSetting extends BaseEntity {
    @Column(name = "sys_key")
    private String sysKey;

    @Column(name = "sys_value")
    private String sysValue;

    @Column(name = "sys_value_json")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> sysValueJson;

    @Override
    public SysDatasourceNoCode getPrefixNoCode() {
        return SysDatasourceNoCode.SYS_SETTING;
    }
}
