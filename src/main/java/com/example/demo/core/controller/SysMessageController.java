package com.example.demo.core.controller;

import com.example.demo.core.dto.body.SysMessageRequestBodyDto;
import com.example.demo.core.dto.response.SysMessageDto;
import com.example.demo.core.service.sysmessage.SysMessageService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("sys-message")
public class SysMessageController {
        private final SysMessageService sysMessageService;

        @PostMapping
        public UUID create(
                        @RequestBody @Valid SysMessageRequestBodyDto sysMessageDto) {
                return sysMessageService
                                .create(sysMessageDto)
                                .getId();
        }

        @GetMapping("{id}")
        public SysMessageDto findById(
                        @PathVariable UUID id) {
                return sysMessageService
                                .findById(id);
        }

}
