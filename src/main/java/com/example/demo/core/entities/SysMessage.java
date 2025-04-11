package com.example.demo.core.entities;


import com.example.demo.core.base.BaseEntity;
import com.example.demo.core.constants.SysDatasourceNoCode;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Map;


@Getter
@Setter
@Entity
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "sys_message")
@NoArgsConstructor
public class SysMessage extends BaseEntity {
    @Column(name = "message_seq", length = Integer.MAX_VALUE)
    String messageSeq;

    @Column(name = "message_code", nullable = false, length = Integer.MAX_VALUE)
    String messageCode;

    @Column(name = "translations", nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    Map<String, String> translations;

    @ColumnDefault("200")
    @Column(name = "status_code", nullable = false)
    Integer statusCode;

    @Override
    public SysDatasourceNoCode getPrefixNoCode() {
        return SysDatasourceNoCode.SYS_MESSAGE;
    }

}
