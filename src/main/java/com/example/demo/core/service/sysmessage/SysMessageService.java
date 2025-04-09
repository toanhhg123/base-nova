package com.example.demo.core.service.sysmessage;

import com.example.demo.core.base.CurdService;
import com.example.demo.core.dto.body.SysMessageCreateDto;
import com.example.demo.core.dto.model.DataWithPagination;
import com.example.demo.core.dto.params.QueryParams;
import com.example.demo.core.dto.response.SysMessageDto;
import com.example.demo.core.entities.SysMessage;
import com.example.demo.core.repositories.SysMessageRepository;

import java.util.UUID;

/**
 * Interface cho service SysMessage.
 */
public interface SysMessageService extends CurdService<SysMessage, SysMessageRepository> {

    @Override
    default Class<SysMessage> getEntityClass() {
        return SysMessage.class;
    }

    DataWithPagination<SysMessageDto> findAll(QueryParams queryParams);

    SysMessageDto createMessage(SysMessageCreateDto dto);

    SysMessageDto findMessageById(UUID id);

    SysMessageDto updateMessage(UUID id, SysMessageCreateDto dto);

    void deleteMessage(UUID id);
}