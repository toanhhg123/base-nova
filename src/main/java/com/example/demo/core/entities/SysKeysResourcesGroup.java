package com.example.demo.core.entities;

import com.example.demo.core.base.BaseEntity;
import com.example.demo.core.constants.SysDatasourceNoCode;
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
@Table(name = "sys_keys_resources_group")
public class SysKeysResourcesGroup extends BaseEntity {
    @OneToMany(mappedBy = "group")
    private Set<SysKeysAccess> sysKeysAccesses = new LinkedHashSet<>();

    @OneToMany(mappedBy = "group")
    private Set<SysKeysResourcesGroupDetail> sysKeysResourcesGroupDetails = new LinkedHashSet<>();

    @Override
    public SysDatasourceNoCode getPrefixNoCode() {
        return SysDatasourceNoCode.DEFAULT;
    }
}