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

@Setter
@Getter
@Entity
@NoArgsConstructor
@SuperBuilder
@Table(name = "sys_roles")
public class SysRole extends BaseEntity {
    @OneToMany(mappedBy = "role")
    private List<SysPermissionsTypeRole> sysPermissionsTypeRoles = new ArrayList<>();

    @OneToMany(mappedBy = "role")
    private List<SysRolesResource> sysRolesResources = new ArrayList<>();

    @Override
    public SysDatasourceNoCode getPrefixNoCode() {
        return SysDatasourceNoCode.SYS_ROLE;
    }
}
