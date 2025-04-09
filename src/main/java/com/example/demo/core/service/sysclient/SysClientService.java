package com.example.demo.core.service.sysclient;

import com.example.demo.core.base.CurdService;
import com.example.demo.core.dto.body.SysClientCreateDto;
import com.example.demo.core.dto.model.DataWithPagination;
import com.example.demo.core.dto.params.QueryParams;
import com.example.demo.core.dto.response.SysClientDto;
import com.example.demo.core.entities.SysClient;
import com.example.demo.core.repositories.SysClientRepository;

import java.util.UUID;

/**
 * Interface service cho SysClient.
 * Kế thừa từ CurdService để sử dụng các phương thức CRUD mặc định.
 */
public interface SysClientService extends CurdService<SysClient, SysClientRepository> {

    /**
     * Trả về class của entity SysClient.
     *
     * @return Class SysClient
     */
    @Override
    default Class<SysClient> getEntityClass() {
        return SysClient.class;
    }

    /**
     * Phân trang danh sách client.
     *
     * @param queryParams điều kiện phân trang và tìm kiếm
     * @return kết quả phân trang
     */
    DataWithPagination<SysClientDto> findAllClients(QueryParams queryParams);

    /**
     * Tạo mới client.
     *
     * @param dto dữ liệu tạo mới
     * @return client đã tạo
     */
    SysClientDto createClient(SysClientCreateDto dto);

    /**
     * Tìm client theo ID.
     *
     * @param id UUID của client
     * @return client tương ứng
     */
    SysClientDto findClient(UUID id);

    /**
     * Cập nhật client.
     *
     * @param id  UUID client
     * @param dto dữ liệu cập nhật
     * @return client đã cập nhật
     */
    SysClientDto updateClient(UUID id, SysClientCreateDto dto);

    /**
     * Xóa client theo ID.
     *
     * @param id UUID client
     */
    void deleteClient(UUID id);
}