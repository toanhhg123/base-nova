package com.example.demo.core.repositories;

import com.example.demo.core.entities.SysMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SysMessageRepository extends JpaRepository<SysMessage, UUID> {
}
