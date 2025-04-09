package com.example.demo.core.entities;

import com.example.demo.core.base.BaseEntity;
import com.example.demo.core.constants.SysDatasourceNoCode;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Map;

@Getter
@Setter
@Entity
@Table(name = "sys_clients")
public class SysClient extends BaseEntity {
    @Column(name = "client_id", nullable = false, length = Integer.MAX_VALUE)
    private String clientId;

    @Column(name = "public_key", nullable = false, length = Integer.MAX_VALUE)
    private String publicKey;

    @Column(name = "ip_whitelist", length = Integer.MAX_VALUE)
    private String ipWhitelist;

    @Column(name = "allowed_endpoints")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> allowedEndpoints;

    @Override
    public SysDatasourceNoCode getPrefixNoCode() {
        return SysDatasourceNoCode.SYS_CLIENT;
    }
}