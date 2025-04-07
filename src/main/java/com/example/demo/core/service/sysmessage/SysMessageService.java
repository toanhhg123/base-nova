package com.example.demo.core.service.sysmessage;

import com.example.demo.core.base.CurdService;
import com.example.demo.core.dto.response.SysMessageDto;
import com.example.demo.core.entities.SysMessage;
import com.example.demo.core.repositories.SysMessageRepository;

import java.util.UUID;

public interface SysMessageService extends CurdService<SysMessage, SysMessageRepository> {
    @Override
    default Class<SysMessage> getEntityClass() {
        return SysMessage.class;
    }

    SysMessageDto findById(UUID id);
}
