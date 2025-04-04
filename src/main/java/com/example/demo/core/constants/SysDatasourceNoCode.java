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
    SYS_PERMISSIONS_TYPE("PERM_TYPE"),
    SYS_SETTING("SET"),
    SYS_SETTING_REPORT("SET_RPT"),
    SYS_TEMPLATE("TPL"), SYS_CLIENT("CLI");
    public final String prefixNoCode;
}
