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
     * @param sysMessage       Thông báo hệ thống
     * @param localizedMessage Thông báo đã được địa phương hóa
     * @param parameters       Tham số thông báo
     * @param messLang         Bản dịch các thông báo theo ngôn ngữ
     * @return Đối tượng Response chứa thông tin lỗi
     */
    protected Response buildErrorResponse(
            SysMessage sysMessage,
            String localizedMessage,
            Map<String, ?> parameters,
            Map<String, String> messLang
    ) {
        return Response.builder()
                .status(ResponseStatus.ERROR)
                .statusCode(ResponseCode.VALIDATE_ERROR.statusCode.value())
                .message(localizedMessage)
                .messCode(ResponseCode.VALIDATE_ERROR.messageCode)
                .messSeq(sysMessage.getMessageSeq())
                .messParam(parameters)
                .messLang(messLang)
                .build();
    }

    protected Response buildErrorResponse(
            ResponseCode responseCode,
            WebRequest request,
            Map<String, String> parameterErrors
    ) {

        var lang = helper.getLanguageFromRequest(request);
        var sysMessage = messageCacheService.getByMessageCode(responseCode.messageCode);
        var localizedMessage = helper.buildLocalizedMessage(sysMessage, lang, parameterErrors);


        return Response.builder()
                .status(ResponseStatus.ERROR)
                .statusCode(responseCode.statusCode.value())
                .message(localizedMessage)
                .messCode(responseCode.messageCode)
                .messSeq(sysMessage.getMessageSeq())
                .messParam(parameterErrors)
                .messLang(helper.getAllTranslations(sysMessage, parameterErrors))
                .build();
    }

}