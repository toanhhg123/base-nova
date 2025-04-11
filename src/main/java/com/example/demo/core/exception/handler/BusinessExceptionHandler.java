package com.example.demo.core.exception.handler;

import com.example.demo.core.dto.model.Response;
import com.example.demo.core.entities.SysMessage;
import com.example.demo.core.exception.AbstractExceptionHandler;
import com.example.demo.core.exception.BusinessException;
import com.example.demo.core.exception.ExceptionHandlerHelper;
import com.example.demo.core.loader.sysmessage.MessageCacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

/**
 * Xử lý ngoại lệ toàn cục cho BusinessException.
 * Cung cấp phản hồi lỗi chuẩn hóa với hỗ trợ đa ngôn ngữ.
 */
@RestControllerAdvice
@Slf4j
public class BusinessExceptionHandler extends AbstractExceptionHandler {

    /**
     * Constructor tiêu chuẩn với các dependency cần thiết.
     *
     * @param messageCacheService Dịch vụ cache thông báo
     * @param helper              Trình hỗ trợ xử lý ngoại lệ
     */
    public BusinessExceptionHandler(MessageCacheService messageCacheService, ExceptionHandlerHelper helper) {
        super(messageCacheService, helper);
    }

    /**
     * Xử lý BusinessException và tạo phản hồi lỗi chuẩn hóa.
     *
     * @param ex      BusinessException bắt được
     * @param request Yêu cầu web hiện tại
     * @return ResponseEntity chứa thông tin lỗi
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Response> handleBusinessException(
            BusinessException ex,
            WebRequest request
    ) {

        // Lấy thông báo hệ thống từ cache
        SysMessage sysMessage = messageCacheService.getByMessageCode(ex.getResponseCode());

        // Xác định ngôn ngữ từ request
        String lang = helper.getLanguageFromRequest(request);

        // Xây dựng thông báo đã được địa phương hóa
        String localizedMessage = helper.buildLocalizedMessage(
                sysMessage,
                lang,
                ex.getParameters()
        );

        // Lấy bản dịch thông báo
        var messLang = helper.getAllTranslations(sysMessage, ex.getParameters());


        // Tạo phản hồi lỗi chuẩn hóa
        Response response = buildErrorResponse(
                ex.getResponseCode(),
                sysMessage,
                localizedMessage,
                ex.getParameters(),
                messLang
        );

        return ResponseEntity
                .status(ex.getResponseCode().statusCode)
                .body(response);
    }
}