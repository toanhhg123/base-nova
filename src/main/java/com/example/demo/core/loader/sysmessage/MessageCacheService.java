package com.example.demo.core.loader.sysmessage;
import com.example.demo.core.constants.HttpStatusMessage;
import com.example.demo.core.entities.SysMessage;

import java.util.List;

/**
 * Interface định nghĩa các operation caching cho SysMessage
 *
 * Các implementation có thể thay đổi (in-memory, Redis,...) mà không ảnh hưởng
 * đến code gọi đến cache service
 */
public interface MessageCacheService {

    /**
     * Khởi tạo hoặc làm mới cache
     */
    void initCache();

    /**
     * Lấy message theo message code
     * @param messageCode mã message cần tìm
     * @return SysMessage hoặc null nếu không tìm thấy
     */
    SysMessage getByMessageCode(HttpStatusMessage messageCode);

    /**
     * Lấy tất cả messages trong cache
     * @return Danh sách tất cả messages
     */
    List<SysMessage> getAllMessages();

    /**
     * Làm mới dữ liệu cache từ database
     */
    void refreshCache();
}