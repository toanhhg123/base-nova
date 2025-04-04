package com.example.demo.core.entities;

import com.example.demo.core.constants.SysDatasourceNoCode;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "sys_keys")
public class SysKey extends CoreEntity {
    @Column(name = "service_name", length = Integer.MAX_VALUE)
    private String serviceName;

    @Column(name = "client_id")
    private String clientId;

    @Column(name = "client_secret")
    private String clientSecret;

    @Column(name = "environment", length = 30)
    private String environment;

    @OneToMany(mappedBy = "sysKey")
    private Set<SysKeysAccess> sysKeysAccesses = new LinkedHashSet<>();

    @Override
    public SysDatasourceNoCode getPrefixNoCode() {
        return SysDatasourceNoCode.DEFAULT;
    }
}