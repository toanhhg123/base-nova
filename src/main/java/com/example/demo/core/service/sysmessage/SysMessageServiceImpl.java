package com.example.demo.core.service.sysmessage;

import com.example.demo.core.base.CurdService;
import com.example.demo.core.dto.body.SysMessageDto;
import com.example.demo.core.dto.response.SysMessageResponse;
import com.example.demo.core.entities.SysMessage;
import com.example.demo.core.repositories.SysMessageRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SysMessageServiceImpl extends CurdService<SysMessage> implements SysMessageService {
    private final ModelMapper modelMapper;

    public SysMessageServiceImpl(SysMessageRepository sysMessageRepository, ModelMapper modelMapper) {
        super(sysMessageRepository);
        this.modelMapper = modelMapper;
    }

    @Override
    public SysMessageResponse findById(UUID id) {
        var sysMessage = super.getById(id);
        return modelMapper.map(sysMessage, SysMessageResponse.class);
    }

    @Override
    public UUID create(SysMessageDto sysMessageDto) {
        return super.create(sysMessageDto).getId();
    }
}
