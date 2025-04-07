package com.example.demo.core.resolver.device;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAgentInfo {
    private String browser;
    private String browserVersion;
    private String os;
    private String osVersion;
    private String device;
}