package com.example.demo.core.resolver.device;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * Main device information model
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceInfo {
    private String ip;
    private UserAgentInfo userAgent;
    private Platform platform;
    private String language;
    private String timestamp;
    private String timezone;
    private String host;
    private String protocol;
    private boolean secure;
    private String method;
    private String path;
    private Map<String, String> query;
}