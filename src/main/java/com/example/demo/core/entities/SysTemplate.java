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

@Setter
@Getter
@Entity
@NoArgsConstructor
@SuperBuilder
@Table(name = "sys_templates")
public class SysTemplate extends BaseEntity {
    @Column(name = "\"fileName\"", nullable = false)
    private String fileName;

    @Column(name = "\"fileType\"", nullable = false)
    private String fileType;

    @Column(name = "\"fileLocation\"", nullable = false)
    private String fileLocation;

    @Override
    public SysDatasourceNoCode getPrefixNoCode() {
        return SysDatasourceNoCode.SYS_TEMPLATE;
    }
}
