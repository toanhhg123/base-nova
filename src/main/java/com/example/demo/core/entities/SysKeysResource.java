package com.example.demo.core.entities;

import com.example.demo.core.base.BaseEntity;
import com.example.demo.core.constants.SysDatasourceNoCode;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@SuperBuilder
@Table(name = "sys_keys_resources")
public class SysKeysResource extends BaseEntity {
    @Column(name = "resource", nullable = false)
    private String resource;

    @Column(name = "scope", nullable = false)
    private String scope;

    @OneToMany(mappedBy = "resource")
    @Builder.Default
    private List<SysKeysResourcesGroupDetail> sysKeysResourcesGroupDetails = new ArrayList<>();

    @Override
    public SysDatasourceNoCode getPrefixNoCode() {
        return SysDatasourceNoCode.DEFAULT;
    }
}