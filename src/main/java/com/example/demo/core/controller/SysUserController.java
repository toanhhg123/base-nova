package com.example.demo.core.controller;

import com.example.demo.core.dto.response.SysUserResponse;
import com.example.demo.core.service.sysuser.SysUserService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@AllArgsConstructor
@RestController
@RequestMapping("sys-user")
public class SysUserController {
    SysUserService service;

    @GetMapping
    public List<SysUserResponse> findAll() {
        return service.findAll();
    }


}
