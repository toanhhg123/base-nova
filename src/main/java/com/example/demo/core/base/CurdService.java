package com.example.demo.core.base;

import com.example.demo.core.constants.ResponseCode;
import com.example.demo.core.dto.model.DataWithPagination;
import com.example.demo.core.exception.BusinessException;
import com.example.demo.core.utils.MapperUtils;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

/**
 * Interface cung cấp các phương thức CRUD cơ bản cho các entity
 *
 * @param <T> Loại entity kế thừa từ BaseEntity
 * @param <R> Loại repository tương ứng với entity
 */
public interface CurdService<T extends BaseEntity, R extends BaseRepository<T>> {

    /**
     * Lấy repository tương ứng với entity
     *
     * @return repository của entity
     */
    R getRepository();

    /**
     * Lấy ModelMapper để ánh xạ dữ liệu
     *
     * @return đối tượng ModelMapper
     */
    default ModelMapper getModelMapper() {
        return MapperUtils.getDefaultMapper();
    }

    // Các phương thức mặc định (có thể ghi đè nếu cần)

    /**
     * Lấy tất cả bản ghi
     *
     * @return Danh sách tất cả entity
     */
    default List<T> getAll() {
        return getRepository().findAll();
    }

    /**
     * Tìm entity theo ID
     *
     * @param id UUID của entity cần tìm
     * @return Entity tìm thấy
     * @throws BusinessException nếu không tìm thấy entity
     */
    default T getById(UUID id) {
        return getRepository().findById(id)
                .orElseThrow(() -> new BusinessException(ResponseCode.DATA_NOT_FOUND, Map.of("fields", id.toString())));
    }

    /**
     * Phân trang dữ liệu với điều kiện tìm kiếm
     *
     * @param spec        Điều kiện tìm kiếm
     * @param queryParams Thông tin phân trang
     * @return Dữ liệu phân trang
     */
    default DataWithPagination<T> findWithPagination(Specification<T> spec, Pageable queryParams) {
        var page = getRepository().findAll(spec, queryParams);
        return DataWithPagination.<T>builder()
                .total(page.getTotalElements())
                .totalPage(page.getTotalPages())
                .data(page.getContent())
                .build();
    }

    /**
     * Tạo mới entity từ DTO
     *
     * @param baseEntityRequestDto DTO chứa dữ liệu
     * @return Entity đã được tạo
     */
    default T create(BaseEntityRequestDto baseEntityRequestDto) {
        T t = getModelMapper().map(baseEntityRequestDto, getEntityClass());
        return getRepository().save(t);
    }

    /**
     * Tạo mới entity với mapper tùy chỉnh
     *
     * @param baseEntityRequestDto DTO chứa dữ liệu
     * @param mapper               Hàm ánh xạ từ DTO sang Entity
     * @return Entity đã được tạo
     */
    default T create(BaseEntityRequestDto baseEntityRequestDto, Function<BaseEntityRequestDto, T> mapper) {
        T t = mapper.apply(baseEntityRequestDto);
        return getRepository().save(t);
    }

    /**
     * Cập nhật entity
     *
     * @param id                   UUID của entity cần cập nhật
     * @param baseEntityRequestDto DTO chứa dữ liệu cập nhật
     * @return Entity đã được cập nhật
     */
    default T update(UUID id, BaseEntityRequestDto baseEntityRequestDto) {
        var t = getById(id);
        MapperUtils.mapNonNullFields(baseEntityRequestDto, t);
        return getRepository().save(t);
    }

    /**
     * Xóa entity theo ID
     *
     * @param id UUID của entity cần xóa
     * @throws BusinessException nếu không tìm thấy entity
     */
    default void delete(UUID id) {
        var t = getRepository().findById(id)
                .orElseThrow(() -> new BusinessException(ResponseCode.DATA_NOT_FOUND, Map.of("fields", id.toString())));
        getRepository().delete(t);
    }

    /**
     * Lấy class của entity
     *
     * @return Class object của entity
     */
    Class<T> getEntityClass();
}