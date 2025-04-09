package com.example.demo.core.controller;

import com.example.demo.core.resolver.device.DeviceInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("auth")
public class AuthController {
    
    @GetMapping("device-info")
    public DeviceInfo deviceInfo(DeviceInfo host) {
        log.info("device-info: {}", host);
        return host;
    }
}
