package com.example.demo.core.dto.response;


import com.example.demo.core.base.BaseEntityDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

/**
 * DTO phản hồi cho entity SysClient.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysClientDto extends BaseEntityDto {
    String clientId;
    String publicKey;
    String ipWhitelist;
    transient Map<String, Object> allowedEndpoints;
}