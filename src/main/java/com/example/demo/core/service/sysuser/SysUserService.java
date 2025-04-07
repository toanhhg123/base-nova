package com.example.demo.core.service.sysuser;

import com.example.demo.core.base.CurdService;
import com.example.demo.core.dto.model.DataWithPagination;
import com.example.demo.core.dto.request.QueryParams;
import com.example.demo.core.dto.response.SysUserDto;
import com.example.demo.core.entities.SysUser;
import com.example.demo.core.repositories.SysUserRepository;

import java.util.UUID;

public interface SysUserService extends CurdService<SysUser, SysUserRepository> {

    @Override
    default Class<SysUser> getEntityClass() {
        return SysUser.class;
    }

    SysUserDto findById(UUID id);

    DataWithPagination<SysUserDto> findAll(QueryParams queryParams);
}
