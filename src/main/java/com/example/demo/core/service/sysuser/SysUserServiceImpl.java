package com.example.demo.core.service.sysuser;

import com.example.demo.core.dto.model.DataWithPagination;
import com.example.demo.core.dto.params.QueryParams;
import com.example.demo.core.dto.response.SysUserDto;
import com.example.demo.core.repositories.SysUserRepository;
import com.example.demo.core.utils.PaginationUtils;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class SysUserServiceImpl implements SysUserService {
    private final SysUserRepository repository;
    private final ModelMapper modelMapper;


    @Override
    public SysUserDto findById(UUID id) {
        var user = getById(id);
        return modelMapper.map(user, SysUserDto.class);
    }

    @Override
    public DataWithPagination<SysUserDto> findAll(QueryParams queryParams) {
        var spec = Specification
                .where(SysUserSpecifications.withRoles())
                .and(SysUserSpecifications.hasSearchTerm(queryParams.getSearch()))
                .and(SysUserSpecifications.modifiedAfter(queryParams.getStartTime()))
                .and(SysUserSpecifications.modifiedBefore(queryParams.getEndTime()));

        var page = repository.findAll(spec, queryParams.toPageable());

        return PaginationUtils.mapPageToDataWithPagination(
                page,
                user -> modelMapper.map(user, SysUserDto.class));


    }

    @Override
    public SysUserRepository getRepository() {
        return repository;
    }

    @Override
    public ModelMapper getModelMapper() {
        return modelMapper;
    }

}
