package com.example.demo.core.entities;

import com.example.demo.core.constants.SysDatasourceNoCode;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.Instant;
import java.util.Map;

@Getter
@Setter
@Entity
@Table(name = "sys_permissions_type_users")
public class SysPermissionsTypeUser extends CoreEntity {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private SysUser user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "resource_id", nullable = false)
    private SysResource resource;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "permission_type_id", nullable = false)
    private SysPermissionsType permissionType;

    @ColumnDefault("0")
    @Column(name = "priority", nullable = false)
    private Integer priority;

    @ColumnDefault("false")
    @Column(name = "is_expired", nullable = false)
    private Boolean isExpired = false;

    @Column(name = "start_date")
    private Instant startDate;

    @Column(name = "end_date")
    private Instant endDate;

    @Column(name = "specific_user_ids_allow")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> specificUserIdsAllow;

    @Column(name = "specific_user_ids_deny")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> specificUserIdsDeny;

    @Override
    public SysDatasourceNoCode getPrefixNoCode() {
        return SysDatasourceNoCode.DEFAULT;
    }
}