package com.example.demo.core.service.sysmessage;

import com.example.demo.core.dto.body.SysMessageDto;
import com.example.demo.core.dto.response.SysMessageResponse;

import java.util.UUID;

public interface SysMessageService {
    SysMessageResponse findById(UUID id);

    UUID create(SysMessageDto sysMessageDto);
}
