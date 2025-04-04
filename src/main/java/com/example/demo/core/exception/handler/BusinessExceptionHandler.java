package com.example.demo.core.exception.handler;

import com.example.demo.core.constants.Language;
import com.example.demo.core.constants.ResponseStatus;
import com.example.demo.core.dto.response.Response;
import com.example.demo.core.entities.SysMessage;
import com.example.demo.core.exception.BusinessException;
import com.example.demo.core.loader.sysmessage.MessageCacheService;
import com.example.demo.core.utils.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;
import java.util.Optional;

/**
 * Xử lý ngoại lệ toàn cục cho BusinessException.
 * Cung cấp phản hồi lỗi chuẩn hóa với hỗ trợ đa ngôn ngữ.
 */
@RestControllerAdvice
@AllArgsConstructor
public class BusinessExceptionHandler {

    private final MessageCacheService messageCacheService;

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
        SysMessage sysMessage = messageCacheService.getByMessageCode(ex.getStatusMessage());

        // Xác định ngôn ngữ từ header request (mặc định tiếng Việt nếu không chỉ định)
        String lang = getLanguageFromRequest(request);

        // Xây dựng thông báo lỗi đã được địa phương hóa với các tham số
        String localizedMessage = buildLocalizedMessage(sysMessage, lang, ex.getParameters());

        // Tạo phản hồi lỗi chuẩn hóa
        Response response = buildErrorResponse(ex, sysMessage, localizedMessage);

        return ResponseEntity
                .status(ex.getStatusMessage().statusCode)
                .body(response);
    }

    /**
     * Lấy ngôn ngữ từ header của request.
     *
     * @param request Yêu cầu web
     * @return Mã ngôn ngữ (mặc định là tiếng Việt)
     */
    private String getLanguageFromRequest(WebRequest request) {
        return Optional.ofNullable(request.getHeader(Language.LANG_HEADER.value))
                .orElse(Language.VI.value);
    }

    /**
     * Xây dựng thông báo đã được địa phương hóa với thay thế tham số.
     *
     * @param sysMessage Mẫu thông báo hệ thống
     * @param lang       Ngôn ngữ đích
     * @param parameters Các tham số thông báo
     * @return Chuỗi thông báo đã định dạng
     */
    private String buildLocalizedMessage(SysMessage sysMessage, String lang, Map<String, String> parameters) {
        String messageTemplate = sysMessage.getTranslations().get(lang);
        return StringUtils.replacePlaceholders(messageTemplate, parameters);
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
                .statusCode(ex.getStatusMessage().statusCode.value())
                .message(localizedMessage)
                .messCode(ex.getStatusMessage().messageCode)
                .messSeq(sysMessage.getMessageSeq())
                .messParam(ex.getParameters())
                .messLang(getAllTranslations(sysMessage, ex.getParameters()))
                .build();
    }

    /**
     * Lấy tất cả bản dịch có sẵn cho thông báo với các tham số đã được thay thế.
     *
     * @param sysMessage Mẫu thông báo hệ thống
     * @param params     Các tham số thông báo
     * @return Map mã ngôn ngữ với thông báo đã dịch
     */
    private Map<String, String> getAllTranslations(SysMessage sysMessage, Map<String, String> params) {
        String messageVI = sysMessage.getTranslations().getOrDefault(Language.VI.value, "");
        String messageEN = sysMessage.getTranslations().getOrDefault(Language.EN.value, "");

        return Map.of(
                Language.VI.value, StringUtils.replacePlaceholders(messageVI, params),
                Language.EN.value, StringUtils.replacePlaceholders(messageEN, params)
        );
    }
}