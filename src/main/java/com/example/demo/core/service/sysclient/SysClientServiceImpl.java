package com.example.demo.core.service.sysclient;

import com.example.demo.core.dto.body.SysClientCreateDto;
import com.example.demo.core.dto.model.DataWithPagination;
import com.example.demo.core.dto.params.QueryParams;
import com.example.demo.core.dto.response.SysClientDto;
import com.example.demo.core.entities.QSysClient;
import com.example.demo.core.entities.command.BaseEntityPredicates;
import com.example.demo.core.entities.command.SearchType;
import com.example.demo.core.repositories.SysClientRepository;
import com.example.demo.core.utils.PaginationUtils;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


/**
 * Triển khai SysClientService.
 * Xử lý logic nghiệp vụ cho SysClient.
 */
@Service
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class SysClientServiceImpl implements SysClientService {
    SysClientRepository repository;


    /**
     * Tạo mới client.
     *
     * @param dto dữ liệu tạo
     * @return client đã tạo
     */
    @Override
    public SysClientDto createClient(SysClientCreateDto dto) {
        var data = this.create(dto);
        return modelMapper.map(data, SysClientDto.class);
    }

    /**
     * Lấy chi tiết client theo ID.
     *
     * @param id UUID client
     * @return client tương ứng
     */
    @Override
    public SysClientDto findClient(UUID id) {
        var data = getById(id);
        return modelMapper.map(data, SysClientDto.class);
    }

    /**
     * Phân trang danh sách client theo điều kiện tìm kiếm.
     *
     * @param queryParams điều kiện tìm kiếm và phân trang
     * @return kết quả phân trang
     */
    @Override
    public DataWithPagination<SysClientDto> findAllClients(QueryParams queryParams) {
        var q = QSysClient.sysClient;

        var builder = BaseEntityPredicates.search(
                queryParams.getSearch(),
                SearchType.CONTAINS,
                q.title,
                q.titleEn,
                q.clientId,
                q.publicKey
        );

        var data = repository.findAll(builder, queryParams.toPageable());
        return PaginationUtils.mapPageToDataWithPagination(data, e -> modelMapper.map(e, SysClientDto.class));
    }


    /**
     * Cập nhật client.
     *
     * @param id  UUID client
     * @param dto dữ liệu cập nhật
     * @return client đã cập nhật
     */
    @Override
    public SysClientDto updateClient(UUID id, SysClientCreateDto dto) {
        var data = this.update(id, dto);
        return modelMapper.map(data, SysClientDto.class);
    }

    /**
     * Xóa client (soft delete).
     *
     * @param id UUID client
     */
    @Transactional
    @Override
    public void deleteClient(UUID id) {
        this.delete(id);
    }

    @Override
    public SysClientRepository getRepository() {
        return repository;
    }
}
