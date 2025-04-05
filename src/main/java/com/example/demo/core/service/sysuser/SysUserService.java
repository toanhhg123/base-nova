package com.example.demo.core.service.sysuser;

import com.example.demo.core.dto.response.SysUserResponse;

import java.util.List;
import java.util.UUID;

public interface SysUserService {
    SysUserResponse findById(UUID id);

    List<SysUserResponse> findAll();
}
