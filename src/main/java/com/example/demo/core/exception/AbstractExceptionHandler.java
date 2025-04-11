package com.example.demo.core.exception;

import com.example.demo.core.constants.ResponseCode;
import com.example.demo.core.constants.ResponseStatus;
import com.example.demo.core.dto.model.Response;
import com.example.demo.core.entities.SysMessage;
import com.example.demo.core.loader.sysmessage.MessageCacheService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;


/**
 * Lớp cơ sở trừu tượng cho các xử lý ngoại lệ.
 * Cung cấp các phương thức chung cho các lớp xử lý ngoại lệ cụ thể.
 */
@AllArgsConstructor
@Slf4j
public abstract class AbstractExceptionHandler {

    protected final MessageCacheService messageCacheService;
    protected final ExceptionHandlerHelper helper;

    /**
     * Tạo phản hồi lỗi chuẩn hóa.
     *
     * @param responseCode     Mã phản hồi
     * @param sysMessage       Thông báo hệ thống
     * @param localizedMessage Thông báo đã được địa phương hóa
     * @param parameters       Tham số thông báo
     * @return Đối tượng Response chứa thông tin lỗi
     */
    /**
     * Tạo phản hồi lỗi chuẩn hóa.
     *
     * @param responseCode     Mã phản hồi
     * @param sysMessage       Thông báo hệ thống
     * @param localizedMessage Thông báo đã được địa phương hóa
     * @param parameters       Tham số thông báo
     * @param messLang         Bản dịch các thông báo theo ngôn ngữ
     * @return Đối tượng Response chứa thông tin lỗi
     */
    protected Response buildErrorResponse(
            ResponseCode responseCode,
            SysMessage sysMessage,
            String localizedMessage,
            Map<String, ?> parameters,
            Map<String, String> messLang
    ) {
        log.debug("Building error response with code: {}, message: {}",
                responseCode.messageCode, localizedMessage);

        return Response.builder()
                .status(ResponseStatus.ERROR)
                .statusCode(responseCode.statusCode.value())
                .message(localizedMessage)
                .messCode(responseCode.messageCode)
                .messSeq(sysMessage.getMessageSeq())
                .messParam(parameters)
                .messLang(messLang)
                .build();
    }

    /**
     * Xử lý quá trình chuẩn hóa và địa phương hóa thông báo lỗi.
     *
     * @param responseCode Mã phản hồi
     * @param request      Yêu cầu web
     * @param parameters   Tham số thông báo
     * @return Thông báo đã được định dạng và địa phương hóa
     */
    protected String getLocalizedMessage(
            ResponseCode responseCode,
            WebRequest request,
            Map<String, String> parameters
    ) {
        // Lấy thông báo hệ thống từ cache
        SysMessage sysMessage = messageCacheService.getByMessageCode(responseCode);

        // Xác định ngôn ngữ từ request
        String lang = helper.getLanguageFromRequest(request);

        // Xây dựng thông báo đã được địa phương hóa
        return helper.buildLocalizedMessage(sysMessage, lang, parameters);
    }
}