package com.example.demo.core.service.sysresource;

import com.example.demo.core.constants.ItemType;
import com.example.demo.core.dto.body.SysResourceCreateDtoEntity;
import com.example.demo.core.dto.model.DataWithPagination;
import com.example.demo.core.dto.params.QueryParams;
import com.example.demo.core.dto.response.SysResourceDto;
import com.example.demo.core.repositories.SysResourceRepository;
import com.example.demo.core.utils.PaginationUtils;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Service implementation for {@link com.example.demo.core.entities.SysResource}.
 * Handles business logic related to system resources.
 */
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
@Service
public class SysResourceServiceImpl implements SysResourceService {
    /**
     * Repository for accessing SysResource-related queries.
     */
    SysResourceRepository sysResourceRepository;
    ModelMapper modelMapper;


    /**
     * Finds paginated MENU-type system resources that the specified user has access to.
     * Applies sorting by lvl, sn, and sub_sn.
     *
     * @param userId      the UUID of the user.
     * @param queryParams pagination and filtering parameters.
     * @return DataWithPagination of SysResourceDto mapped from the SysResource entity.
     */
    @Override
    public DataWithPagination<SysResourceDto> findMenuByUser(UUID userId, QueryParams queryParams) {
        // Define sorting order: by level, sequence number, and sub-sequence number
        Sort sort = Sort.by(
                Sort.Order.asc("lvl"),
                Sort.Order.asc("sn"),
                Sort.Order.asc("sub_sn")
        );

        // Create a Pageable object using current page, limit, and sort
        var pageable = PageRequest.of(
                queryParams.getCurrentPage(), // current page number
                queryParams.getLimit(),       // items per page
                sort                          // sorting rules
        );

        // Fetch paginated resources of type MENU that the user has access to
        var page = sysResourceRepository.findAllResourcesByUserId(
                userId,
                ItemType.MENU.getValue(), // map enum value of MENU
                pageable
        );

        // Map entity results to DTOs and wrap them with pagination metadata
        return PaginationUtils.mapPageToDataWithPagination(
                page,
                resource -> modelMapper.map(resource, SysResourceDto.class) // convert entity to DTO
        );
    }

    @Override
    public SysResourceDto findById(UUID id) {
        var data = this.getById(id);
        return modelMapper.map(data, SysResourceDto.class);
    }

    @Override
    public SysResourceDto createResource(SysResourceCreateDtoEntity resourceDto) {
        var data = this.create(resourceDto);
        return modelMapper.map(data, SysResourceDto.class);
    }

    @Override
    public SysResourceDto updateResource(UUID id, SysResourceCreateDtoEntity sysResourceCreateDto) {
        var data = this.update(id, sysResourceCreateDto);
        return modelMapper.map(data, SysResourceDto.class);
    }


    @Override
    public SysResourceRepository getRepository() {
        return sysResourceRepository;
    }

    @Override
    public ModelMapper getModelMapper() {
        return modelMapper;
    }
}
