package com.example.demo.core.controller;

import com.example.demo.core.dto.body.SysMessageCreateDto;
import com.example.demo.core.dto.model.DataWithPagination;
import com.example.demo.core.dto.model.ResponseEntityData;
import com.example.demo.core.dto.model.ResponseEntityPagination;
import com.example.demo.core.dto.params.QueryParams;
import com.example.demo.core.dto.response.SysMessageDto;
import com.example.demo.core.service.sysmessage.SysMessageService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


/**
 * Controller xử lý các API cho SysMessage.
 */
@RestController
@RequestMapping("messages")
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class SysMessageController {

    SysMessageService service;

    /**
     * Lấy danh sách SysMessage với phân trang & tìm kiếm.
     *
     * @param queryParams điều kiện phân trang và tìm kiếm
     * @return danh sách bản ghi
     */
    @GetMapping
    public ResponseEntityPagination<SysMessageDto> findAll(QueryParams queryParams) {
        DataWithPagination<SysMessageDto> response = service.findAll(queryParams);
        return ResponseEntityPagination.success(response);
    }

    /**
     * Tìm SysMessage theo ID.
     *
     * @param id UUID của bản ghi
     * @return SysMessageDto tương ứng
     */
    @GetMapping("{id}")
    public ResponseEntityData<SysMessageDto> findById(@PathVariable UUID id) {
        SysMessageDto response = service.findMessageById(id);
        return ResponseEntityData.success(response);
    }

    /**
     * Tạo mới SysMessage.
     *
     * @param body dữ liệu đầu vào
     * @return SysMessageDto đã tạo
     */
    @PostMapping
    public ResponseEntityData<SysMessageDto> create(@RequestBody @Valid SysMessageCreateDto body) {
        SysMessageDto response = service.createMessage(body);
        return ResponseEntityData.success(response);
    }

    /**
     * Cập nhật SysMessage theo ID.
     *
     * @param id   UUID bản ghi
     * @param body dữ liệu cập nhật
     * @return SysMessageDto đã cập nhật
     */
    @PutMapping("{id}")
    public ResponseEntityData<SysMessageDto> update(
            @PathVariable UUID id,
            @RequestBody @Valid SysMessageCreateDto body
    ) {
        SysMessageDto response = service.updateMessage(id, body);
        return ResponseEntityData.success(response);
    }

    /**
     * Xóa SysMessage theo ID.
     *
     * @param id UUID bản ghi
     * @return UUID đã xóa
     */
    @DeleteMapping("{id}")
    public ResponseEntityData<UUID> delete(@PathVariable UUID id) {
        service.deleteMessage(id);
        return ResponseEntityData.success(id);
    }
}