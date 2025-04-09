package com.example.demo.core.service.syshistory;

import com.example.demo.core.base.CurdService;
import com.example.demo.core.dto.body.SysHistoryCreateDto;
import com.example.demo.core.dto.model.DataWithPagination;
import com.example.demo.core.dto.params.QueryParams;
import com.example.demo.core.dto.response.SysHistoryDto;
import com.example.demo.core.entities.SysHistory;
import com.example.demo.core.repositories.SysHistoryRepository;

import java.util.UUID;

public interface SysHistoryService extends CurdService<SysHistory, SysHistoryRepository> {
    @Override
    default Class<SysHistory> getEntityClass() {
        return SysHistory.class;
    }

    DataWithPagination<SysHistoryDto> findAllHistory(QueryParams queryParams);

    SysHistoryDto addHistory(SysHistoryCreateDto sysHistoryCreateDto);

    SysHistoryDto findHistory(UUID id);

    SysHistoryDto updateHistory(UUID id, SysHistoryCreateDto sysHistoryCreateDto);

    void deleteHistory(UUID id);
}
