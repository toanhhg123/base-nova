package com.example.demo.core.service;

import com.example.demo.core.base.CurdService;
import com.example.demo.core.entities.SysHistory;
import com.example.demo.core.repositories.SysHistoryRepository;
import org.springframework.stereotype.Service;

@Service
public class HistoryService extends CurdService<SysHistory> {
    public HistoryService(SysHistoryRepository sysHistoryRepository) {
        super(sysHistoryRepository);
    }
}
