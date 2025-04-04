package com.example.demo.core.entities;

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
public class SysClient extends CoreEntity {
    @Column(name = "\"clientId\"", nullable = false, length = Integer.MAX_VALUE)
    private String clientId;

    @Column(name = "\"publicKey\"", nullable = false, length = Integer.MAX_VALUE)
    private String publicKey;

    @Column(name = "\"ipWhitelist\"", length = Integer.MAX_VALUE)
    private String ipWhitelist;

    @Column(name = "\"allowedEndpoints\"")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> allowedEndpoints;

    @Override
    public SysDatasourceNoCode getPrefixNoCode() {
        return SysDatasourceNoCode.SYS_CLIENT;
    }
}