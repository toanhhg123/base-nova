package com.example.demo.core.service.sysmessage;

import com.example.demo.core.dto.body.SysMessageCreateDto;
import com.example.demo.core.dto.model.DataWithPagination;
import com.example.demo.core.dto.params.QueryParams;
import com.example.demo.core.dto.response.SysMessageDto;
import com.example.demo.core.entities.QSysMessage;
import com.example.demo.core.entities.command.BaseEntityPredicates;
import com.example.demo.core.entities.command.SearchType;
import com.example.demo.core.repositories.SysMessageRepository;
import com.example.demo.core.utils.PaginationUtils;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@AllArgsConstructor
public class SysMessageServiceImpl implements SysMessageService {
    SysMessageRepository repository;

    @Override
    public SysMessageRepository getRepository() {
        return repository;
    }

    @Override
    public ModelMapper getModelMapper() {
        return modelMapper;
    }

    @Override
    public DataWithPagination<SysMessageDto> findAll(QueryParams queryParams) {
        var q = QSysMessage.sysMessage;
        var builder = BaseEntityPredicates.search(
                queryParams.getSearch(),
                SearchType.CONTAINS,
                q.title,
                q.titleEn,
                q.messageCode,
                q.messageSeq
        );

        var data = repository.findAll(builder, queryParams.toPageable());
        return PaginationUtils.mapPageToDataWithPagination(data, e -> modelMapper.map(e, SysMessageDto.class));
    }

    @Override
    public SysMessageDto createMessage(SysMessageCreateDto dto) {
        var entity = this.create(dto);
        return modelMapper.map(entity, SysMessageDto.class);
    }

    @Override
    public SysMessageDto findMessageById(UUID id) {
        var entity = getById(id);
        return modelMapper.map(entity, SysMessageDto.class);
    }

    @Override
    public SysMessageDto updateMessage(UUID id, SysMessageCreateDto dto) {
        var entity = this.update(id, dto);
        return modelMapper.map(entity, SysMessageDto.class);
    }

    @Transactional
    @Override
    public void deleteMessage(UUID id) {
        this.delete(id);
    }
}
