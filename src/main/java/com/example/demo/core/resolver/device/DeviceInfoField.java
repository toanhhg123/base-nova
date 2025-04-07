package com.example.demo.core.resolver.device;

/**
 * Enum representing the available fields in DeviceInfo
 */
public enum DeviceInfoField {
    IP,
    USER_AGENT,
    PLATFORM,
    LANGUAGE,
    TIMESTAMP,
    TIMEZONE,
    HOST,
    PROTOCOL,
    SECURE,
    METHOD,
    PATH,
    QUERY,
    ALL; // Default value to return the entire DeviceInfo object
}