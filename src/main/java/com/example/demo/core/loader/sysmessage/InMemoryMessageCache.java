package com.example.demo.core.loader.sysmessage;

import com.example.demo.core.constants.HttpStatusMessage;
import com.example.demo.core.entities.SysMessage;
import com.example.demo.core.repositories.SysMessageRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * In-memory cache implementation sử dụng ConcurrentHashMap
 * <p>
 * Được đánh dấu @Primary nên sẽ được ưu tiên sử dụng khi không có cấu hình đặc biệt
 * <p>
 * Phù hợp cho:
 * - Môi trường development
 * - Dữ liệu nhỏ (dưới 1000 records)
 * - Triển khai đơn giản không phụ thuộc vào bên thứ 3
 * <p>
 * Nếu tài nguyên lớn thì nen sử dụng Redis
 */
@Service
@Primary
public class InMemoryMessageCache implements MessageCacheService {
    private final Map<String, SysMessage> messageCodeCache = new ConcurrentHashMap<>();
    private final SysMessageRepository repository;

    public InMemoryMessageCache(SysMessageRepository repository) {
        this.repository = repository;
    }


    /**
     * Khởi tạo cache khi service được tạo
     */
    @PostConstruct
    @Override
    public void initCache() {
        refreshCache();
    }

    @Override
    public SysMessage getByMessageCode(HttpStatusMessage httpStatusMessage) {
        return this.messageCodeCache.get(httpStatusMessage.messageCode);
    }


    @Override
    public List<SysMessage> getAllMessages() {
        return List.copyOf(messageCodeCache.values());
    }

    /**
     * Làm mới toàn bộ cache từ database
     */
    @Override
    public void refreshCache() {
        List<SysMessage> messages = repository.findAll();
        messageCodeCache.clear();
        messages.forEach(msg -> messageCodeCache.put(msg.getMessageCode(), msg));
    }

}
