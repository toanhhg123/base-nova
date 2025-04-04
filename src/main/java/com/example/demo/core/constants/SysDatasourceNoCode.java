package com.example.demo.core.constants;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum SysDatasourceNoCode {
    DEFAULT("N"),
    SYS_MESSAGE("MSG"),
    SYS_USER("USR"),
    SYS_USER_RESOURCE("USR_RES"),
    SYS_ROLE("ROLE"),
    SYS_RESOURCE("RES"),
    SYS_PERMISSIONS_TYPE("PERM_TYPE"),;
    public final String prefixNoCode;
}
