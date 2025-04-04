package com.example.demo.core.entities;

import com.example.demo.core.constants.SysDatasourceNoCode;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "sys_keys_resources")
public class SysKeysResource extends CoreEntity {
    @Column(name = "resource", nullable = false)
    private String resource;

    @Column(name = "scope", nullable = false)
    private String scope;

    @OneToMany(mappedBy = "resource")
    private Set<SysKeysResourcesGroupDetail> sysKeysResourcesGroupDetails = new LinkedHashSet<>();

    @Override
    public SysDatasourceNoCode getPrefixNoCode() {
        return SysDatasourceNoCode.DEFAULT;
    }
}