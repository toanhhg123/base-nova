package com.example.demo.core.entities;

import com.example.demo.core.base.BaseEntity;
import com.example.demo.core.constants.SysDatasourceNoCode;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "sys_keys_resources_group")
public class SysKeysResourcesGroup extends BaseEntity {
    @OneToMany(mappedBy = "group")
    private List<SysKeysAccess> sysKeysAccesses = new ArrayList<>();

    @OneToMany(mappedBy = "group")
    private List<SysKeysResourcesGroupDetail> sysKeysResourcesGroupDetails = new ArrayList<>();

    @Override
    public SysDatasourceNoCode getPrefixNoCode() {
        return SysDatasourceNoCode.DEFAULT;
    }
}