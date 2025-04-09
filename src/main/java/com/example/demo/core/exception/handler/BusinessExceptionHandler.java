package com.example.demo.core.exception.handler;

import com.example.demo.core.constants.ResponseStatus;
import com.example.demo.core.dto.model.Response;
import com.example.demo.core.entities.SysMessage;
import com.example.demo.core.exception.BusinessException;
import com.example.demo.core.exception.ExceptionHandlerHelper;
import com.example.demo.core.loader.sysmessage.MessageCacheService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

/**
 * Xử lý ngoại lệ toàn cục cho BusinessException.
 * Cung cấp phản hồi lỗi chuẩn hóa với hỗ trợ đa ngôn ngữ.
 */
@RestControllerAdvice
@AllArgsConstructor
public class BusinessExceptionHandler {

    private final MessageCacheService messageCacheService;
    private final ExceptionHandlerHelper helper;

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
        // Lấy thông báo hệ thống từ cache sử dụng mã thông báo của ngoại lệ
        SysMessage sysMessage = messageCacheService.getByMessageCode(ex.getResponseCode());

        // Xác định ngôn ngữ từ header request (mặc định tiếng Việt nếu không chỉ định)
        String lang = helper.getLanguageFromRequest(request);

        // Xây dựng thông báo lỗi đã được địa phương hóa với các tham số
        String localizedMessage = helper.buildLocalizedMessage(sysMessage, lang, ex.getParameters());

        // Tạo phản hồi lỗi chuẩn hóa
        Response response = buildErrorResponse(ex, sysMessage, localizedMessage);

        return ResponseEntity
                .status(ex.getResponseCode().statusCode)
                .body(response);
    }
    
    /**
     * Tạo đối tượng phản hồi lỗi chuẩn hóa.
     *
     * @param ex               Ngoại lệ gốc
     * @param sysMessage       Mẫu thông báo hệ thống
     * @param localizedMessage Thông báo lỗi đã định dạng
     * @return Đối tượng Response chứa thông tin lỗi
     */
    private Response buildErrorResponse(BusinessException ex, SysMessage sysMessage, String localizedMessage) {
        return Response.builder()
                .status(ResponseStatus.ERROR)
                .statusCode(ex.getResponseCode().statusCode.value())
                .message(localizedMessage)
                .messCode(ex.getResponseCode().messageCode)
                .messSeq(sysMessage.getMessageSeq())
                .messParam(ex.getParameters())
                .messLang(helper.getAllTranslations(sysMessage, ex.getParameters()))
                .build();
    }

}