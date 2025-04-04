package com.example.demo.core.entities;

import com.example.demo.core.constants.SysDatasourceNoCode;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "sys_users")
public class SysUser extends CoreEntity {
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

    @Override
    public SysDatasourceNoCode getPrefixNoCode() {
        return SysDatasourceNoCode.SYS_USER;
    }
}
