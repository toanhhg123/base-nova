package com.example.demo.core.entities;

import com.example.demo.core.base.BaseEntity;
import com.example.demo.core.constants.SysDatasourceNoCode;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "sys_roles_resources")
public class SysRolesResource extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "role_id", nullable = false)
    private SysRole role;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "resource_id", nullable = false)
    private SysResource resource;

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

    @Override
    public SysDatasourceNoCode getPrefixNoCode() {
        return SysDatasourceNoCode.DEFAULT;
    }
}