package com.example.demo.core.repositories;

import com.example.demo.core.entities.CoreEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BaseRepository<T extends CoreEntity> extends JpaRepository<T, UUID> {
    Page<T> findAll(Specification<T> spec, Pageable pageable);

    Integer count(Specification<T> spec);
}