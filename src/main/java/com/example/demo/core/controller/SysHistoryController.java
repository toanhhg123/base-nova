package com.example.demo.core.controller;

import com.example.demo.core.service.HistoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("histories")
public class SysHistoryController {
    private final HistoryService historyService;
}
