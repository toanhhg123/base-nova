package com.example.demo.core.repositories;

import com.example.demo.core.base.BaseRepository;
import com.example.demo.core.entities.SysClient;

/**
 * Repository xử lý truy vấn cho entity SysClient.
 * Kế thừa từ BaseRepository để sử dụng các method CRUD mặc định.
 */
public interface SysClientRepository extends BaseRepository<SysClient> {
    // Bạn có thể khai báo thêm các custom query ở đây nếu cần
}