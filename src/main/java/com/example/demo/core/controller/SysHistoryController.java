package com.example.demo.core.controller;

import com.example.demo.core.dto.body.SysHistoryCreateDto;
import com.example.demo.core.dto.model.ResponseEntityData;
import com.example.demo.core.dto.model.ResponseEntityPagination;
import com.example.demo.core.dto.params.QueryParams;
import com.example.demo.core.dto.response.SysHistoryDto;
import com.example.demo.core.service.syshistory.SysHistoryService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
@RestController
@RequestMapping("histories")
public class SysHistoryController {
    SysHistoryService service;

    @GetMapping
    public ResponseEntityPagination<SysHistoryDto> findAll(QueryParams queryParams) {
        var response = service.findAllHistory(queryParams);
        return ResponseEntityPagination.success(response);
    }

    @GetMapping("{id}")
    public ResponseEntityData<SysHistoryDto> findById(@PathVariable UUID id) {
        var response = service.findHistory(id);
        return ResponseEntityData.success(response);
    }

    @PostMapping
    public ResponseEntityData<SysHistoryDto> update(@RequestBody @Valid SysHistoryCreateDto body) {
        var response = service.addHistory(body);
        return ResponseEntityData.success(response);
    }

    @PutMapping("{id}")
    public ResponseEntityData<SysHistoryDto> update(
            @PathVariable UUID id,
            @RequestBody SysHistoryCreateDto body
    ) {
        var response = service.updateHistory(id, body);
        return ResponseEntityData.success(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntityData<UUID> update(
            @PathVariable UUID id
    ) {
        service.deleteHistory(id);
        return ResponseEntityData.success(id);
    }

}
