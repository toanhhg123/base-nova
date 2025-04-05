package com.example.demo.core.entities;

import com.example.demo.core.base.BaseEntity;
import com.example.demo.core.constants.SysDatasourceNoCode;
import jakarta.persistence.Column;
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
@Table(name = "sys_users")
public class SysUser extends BaseEntity {
    @Column(name = "user_name")
    private String userName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "phone_mobile", length = 50)
    private String phoneMobile;

    @Column(name = "address")
    private String address;

    @Column(name = "gender", length = 20)
    private String gender;

    @Column(name = "email")
    private String email;

    @Column(name = "keycloak_id")
    private String keycloakId;

    @OneToMany(mappedBy = "user")
    private Set<SysUserRole> sysUsersRoles = new LinkedHashSet<>();

    @Override
    public SysDatasourceNoCode getPrefixNoCode() {
        return SysDatasourceNoCode.SYS_USER;
    }
}
