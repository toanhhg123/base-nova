package com.example.demo.core.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BaseRepository<T extends BaseEntity> extends JpaRepository<T, UUID> {
    Page<T> findAll(Specification<T> spec, Pageable pageable);

    Integer count(Specification<T> spec);
}