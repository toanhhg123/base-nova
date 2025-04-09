package com.example.demo.core.service.sysresource;

import com.example.demo.core.base.CurdService;
import com.example.demo.core.dto.body.SysResourceCreateDtoEntity;
import com.example.demo.core.dto.model.DataWithPagination;
import com.example.demo.core.dto.params.QueryParams;
import com.example.demo.core.dto.response.SysResourceDto;
import com.example.demo.core.entities.SysResource;
import com.example.demo.core.repositories.SysResourceRepository;

import java.util.UUID;

public interface SysResourceService extends CurdService<SysResource, SysResourceRepository> {

    @Override
    default Class<SysResource> getEntityClass() {
        return SysResource.class;
    }

    DataWithPagination<SysResourceDto> findMenuByUser(UUID userId, QueryParams queryParams);

    SysResourceDto findById(UUID id);


    SysResourceDto createResource(SysResourceCreateDtoEntity sysResourceCreateDto);

    SysResourceDto updateResource(UUID id, SysResourceCreateDtoEntity sysResourceCreateDto);


}
