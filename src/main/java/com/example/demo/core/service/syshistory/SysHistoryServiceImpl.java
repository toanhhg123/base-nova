package com.example.demo.core.service.syshistory;

import com.example.demo.core.dto.body.SysHistoryCreateDto;
import com.example.demo.core.dto.model.DataWithPagination;
import com.example.demo.core.dto.params.QueryParams;
import com.example.demo.core.dto.response.SysHistoryDto;
import com.example.demo.core.entities.QSysHistory;
import com.example.demo.core.entities.command.BaseEntityPredicates;
import com.example.demo.core.entities.command.SearchType;
import com.example.demo.core.repositories.SysHistoryRepository;
import com.example.demo.core.utils.PaginationUtils;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
@AllArgsConstructor
public class SysHistoryServiceImpl implements SysHistoryService {
    SysHistoryRepository repository;
    ModelMapper modelMapper;

    @Override
    public SysHistoryRepository getRepository() {
        return repository;
    }

    @Override
    public ModelMapper getModelMapper() {
        return modelMapper;
    }

    @Override
    public DataWithPagination<SysHistoryDto> findAllHistory(QueryParams queryParams) {
        var qSysHistory = QSysHistory.sysHistory;
        var builder = BaseEntityPredicates.search(
                queryParams.getSearch(),
                SearchType.CONTAINS,
                qSysHistory.title,
                qSysHistory.titleEn,
                qSysHistory.primaryTable,
                qSysHistory.histTable,
                qSysHistory.histSchema,
                qSysHistory.triggerName
        );

        var data = repository.findAll(builder, queryParams.toPageable());
        return PaginationUtils.mapPageToDataWithPagination(
                data,
                history -> modelMapper.map(history, SysHistoryDto.class)
        );
    }

    @Override
    public SysHistoryDto addHistory(SysHistoryCreateDto sysHistoryCreateDto) {
        var data = this.create(sysHistoryCreateDto);
        return modelMapper.map(data, SysHistoryDto.class);
    }

    @Override
    public SysHistoryDto findHistory(UUID id) {
        var data = getById(id);
        return modelMapper.map(data, SysHistoryDto.class);
    }

    @Override
    public SysHistoryDto updateHistory(UUID id, SysHistoryCreateDto sysHistoryCreateDto) {
        var data = this.update(id, sysHistoryCreateDto);
        return modelMapper.map(data, SysHistoryDto.class);
    }

    @Transactional
    @Override
    public void deleteHistory(UUID id) {
        this.delete(id);
    }


}
