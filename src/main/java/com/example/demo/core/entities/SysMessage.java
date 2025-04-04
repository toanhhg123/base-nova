package com.example.demo.core.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Map;

@Setter
@Getter
@Entity
@Table(name = "sys_message")
public class SysMessage extends CoreEntity {
    @Column(name = "message_seq", length = Integer.MAX_VALUE)
    private String messageSeq;

    @Column(name = "message_code", nullable = false, length = Integer.MAX_VALUE)
    private String messageCode;

    @Column(name = "translations", nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> translations;

    @ColumnDefault("200")
    @Column(name = "status_code", nullable = false)
    private Integer statusCode;

    @Override
    public String getPrefixNoCode() {
        return "MSG";
    }
}
