package com.example.demo.core.dto.response;

import com.example.demo.core.base.BaseEntityDto;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

import java.util.Set;

/**
 * DTO for {@link com.example.demo.core.entities.SysUser}
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = true)
@Data
public class SysUserResponse extends BaseEntityDto {
    String userName;
    String firstName;
    String lastName;
    String middleName;
    String fullName;
    String phoneMobile;
    String address;
    String gender;
    String email;
    String keycloakId;
    transient Set<SysUserRoleResponse> sysUsersRoles;
}