package com.example.demo.core.controller;

import com.example.demo.core.context.GlobalUserContextService;
import com.example.demo.core.dto.body.SysResourceCreateDtoEntity;
import com.example.demo.core.dto.model.ResponseEntityData;
import com.example.demo.core.dto.model.ResponseEntityPagination;
import com.example.demo.core.dto.params.QueryParams;
import com.example.demo.core.dto.response.SysResourceDto;
import com.example.demo.core.service.sysresource.SysResourceService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@AllArgsConstructor
@RestController
@RequestMapping("sys-resource")
public class SysResourceController {
    SysResourceService service;
    GlobalUserContextService userContextService;

    @GetMapping("menu")
    public ResponseEntityPagination<SysResourceDto> findMenuUser(QueryParams queryParams) {
        var response = service
                .findMenuByUser(userContextService.getUserId(), queryParams);
        return ResponseEntityPagination.success(response, queryParams);
    }

    @GetMapping("{id}")
    public ResponseEntityData<SysResourceDto> findById(@PathVariable UUID id) {
        var response = service.findById(id);
        return ResponseEntityData.success(response);
    }

    @PostMapping
    public ResponseEntityData<SysResourceDto> create(
            @RequestBody @Valid SysResourceCreateDtoEntity sysResourceCreateDto) {
        var data = service.createResource(sysResourceCreateDto);
        return ResponseEntityData.success(data);
    }

    @PatchMapping("{id}")
    public ResponseEntityData<SysResourceDto> update(
            @RequestBody SysResourceCreateDtoEntity sysResourceCreateDto,
            @PathVariable UUID id) {
        var data = service.updateResource(id, sysResourceCreateDto);
        return ResponseEntityData.success(data);
    }

    @DeleteMapping("{id}")
    public ResponseEntityData<UUID> delete(
            @PathVariable UUID id) {
        service.delete(id);
        return ResponseEntityData.success(id);
    }

}
