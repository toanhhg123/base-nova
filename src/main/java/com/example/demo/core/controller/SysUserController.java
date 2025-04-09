package com.example.demo.core.controller;

import com.example.demo.core.dto.model.ResponseEntityPagination;
import com.example.demo.core.dto.params.QueryParams;
import com.example.demo.core.dto.response.SysUserDto;
import com.example.demo.core.service.sysuser.SysUserService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@AllArgsConstructor
@RestController
@RequestMapping("sys-user")
public class SysUserController {
    SysUserService service;

    @GetMapping
    public ResponseEntityPagination<SysUserDto> findAll(QueryParams query) {
        var data = service.findAll(query);
        return ResponseEntityPagination.success(data, query);
    }
}
