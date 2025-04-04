package com.example.demo.core.entities;

import com.example.demo.core.constants.SysDatasourceNoCode;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "sys_roles")
public class SysRole extends CoreEntity {
    @OneToMany(mappedBy = "role")
    private Set<SysPermissionsTypeRole> sysPermissionsTypeRoles = new LinkedHashSet<>();

    @Override
    public SysDatasourceNoCode getPrefixNoCode() {
        return SysDatasourceNoCode.SYS_ROLE;
    }
}
