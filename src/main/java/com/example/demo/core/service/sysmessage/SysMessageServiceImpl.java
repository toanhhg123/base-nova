package com.example.demo.core.service.sysmessage;

import com.example.demo.core.dto.response.SysMessageDto;
import com.example.demo.core.repositories.SysMessageRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class SysMessageServiceImpl implements SysMessageService {
    private final ModelMapper modelMapper;
    private final SysMessageRepository sysMessageRepository;

    @Override
    public SysMessageDto findById(UUID id) {
        var sysMessage = getById(id);
        return modelMapper.map(sysMessage, SysMessageDto.class);
    }


    @Override
    public SysMessageRepository getRepository() {
        return sysMessageRepository;
    }

    @Override
    public ModelMapper getModelMapper() {
        return modelMapper;
    }
}
