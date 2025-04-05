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

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "sys_users_tokens")
public class SysUsersToken extends BaseEntity {
    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "access_token", nullable = false, length = Integer.MAX_VALUE)
    private String accessToken;

    @Column(name = "refresh_token", length = Integer.MAX_VALUE)
    private String refreshToken;

    @Column(name = "access_token_expired_at", nullable = false)
    private Instant accessTokenExpiredAt;

    @Column(name = "refresh_token_expired_at")
    private Instant refreshTokenExpiredAt;

    @Column(name = "revoked_at")
    private Instant revokedAt;

    @Column(name = "revoked_by_ip", length = Integer.MAX_VALUE)
    private String revokedByIp;

    @Column(name = "revoked_reason", length = Integer.MAX_VALUE)
    private String revokedReason;

    @Column(name = "replaced_by_token", length = Integer.MAX_VALUE)
    private String replacedByToken;

    @Column(name = "device_info")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> deviceInfo;

    @Column(name = "metadata")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> metadata;

    @Column(name = "client_id", length = Integer.MAX_VALUE)
    private String clientId;

    @Override
    public SysDatasourceNoCode getPrefixNoCode() {
        return SysDatasourceNoCode.DEFAULT;
    }
}