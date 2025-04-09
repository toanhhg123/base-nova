package com.example.demo.core.controller;


import com.example.demo.core.dto.body.SysClientCreateDto;
import com.example.demo.core.dto.model.ResponseEntityData;
import com.example.demo.core.dto.model.ResponseEntityPagination;
import com.example.demo.core.dto.params.QueryParams;
import com.example.demo.core.dto.response.SysClientDto;
import com.example.demo.core.service.sysclient.SysClientService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Controller xử lý các API liên quan đến SysClient.
 */
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
@RestController
@RequestMapping("clients")
public class SysClientController {

    SysClientService service;

    /**
     * API lấy danh sách client có phân trang + tìm kiếm.
     *
     * @param queryParams thông tin phân trang và tìm kiếm
     * @return danh sách client
     */
    @GetMapping
    public ResponseEntityPagination<SysClientDto> findAll(QueryParams queryParams) {
        var response = service.findAllClients(queryParams);
        return ResponseEntityPagination.success(response);
    }

    /**
     * API lấy thông tin chi tiết client theo ID.
     *
     * @param id UUID của client
     * @return thông tin client
     */
    @GetMapping("{id}")
    public ResponseEntityData<SysClientDto> findById(@PathVariable UUID id) {
        SysClientDto response = service.findClient(id);
        return ResponseEntityData.success(response);
    }

    /**
     * API tạo mới client.
     *
     * @param body dữ liệu tạo mới
     * @return client đã tạo
     */
    @PostMapping
    public ResponseEntityData<SysClientDto> create(@RequestBody @Valid SysClientCreateDto body) {
        SysClientDto response = service.createClient(body);
        return ResponseEntityData.success(response);
    }

    /**
     * API cập nhật thông tin client.
     *
     * @param id   UUID của client
     * @param body dữ liệu cập nhật
     * @return client đã cập nhật
     */
    @PutMapping("{id}")
    public ResponseEntityData<SysClientDto> update(
            @PathVariable UUID id,
            @RequestBody @Valid SysClientCreateDto body
    ) {
        SysClientDto response = service.updateClient(id, body);
        return ResponseEntityData.success(response);
    }

    /**
     * API xóa client (soft delete).
     *
     * @param id UUID của client
     * @return UUID đã xóa
     */
    @DeleteMapping("{id}")
    public ResponseEntityData<UUID> delete(@PathVariable UUID id) {
        service.deleteClient(id);
        return ResponseEntityData.success(id);
    }
}