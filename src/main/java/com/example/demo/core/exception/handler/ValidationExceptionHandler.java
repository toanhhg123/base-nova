package com.example.demo.core.exception.handler;

import com.example.demo.core.constants.Language;
import com.example.demo.core.constants.ResponseCode;
import com.example.demo.core.dto.model.Response;
import com.example.demo.core.exception.AbstractExceptionHandler;
import com.example.demo.core.exception.ExceptionHandlerHelper;
import com.example.demo.core.loader.sysmessage.MessageCacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * Xử lý ngoại lệ toàn cục cho các lỗi validation.
 * Cung cấp phản hồi lỗi chuẩn hóa với thông tin chi tiết về lỗi validation.
 */
@RestControllerAdvice
@Slf4j
public class ValidationExceptionHandler extends AbstractExceptionHandler {

    /**
     * Constructor tiêu chuẩn với các dependency cần thiết.
     *
     * @param messageCacheService Dịch vụ cache thông báo
     * @param helper              Trình hỗ trợ xử lý ngoại lệ
     */
    public ValidationExceptionHandler(MessageCacheService messageCacheService, ExceptionHandlerHelper helper) {
        super(messageCacheService, helper);
    }

    /**
     * Xử lý ngoại lệ validation từ quá trình kiểm tra request body.
     *
     * @param ex      Ngoại lệ validation
     * @param request Yêu cầu web hiện tại
     * @return ResponseEntity chứa chi tiết lỗi validation
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response> handleValidationExceptions(
            MethodArgumentNotValidException ex,
            WebRequest request) {

        // Lấy ngôn ngữ từ request header
        String lang = helper.getLanguageFromRequest(request);

        // Trích xuất danh sách lỗi field
        var fieldErrors = ex.getBindingResult().getFieldErrors();

        // Lấy thông báo hệ thống cho lỗi validation
        var sysMessage = helper.getSysMessageByStatusMessage(ResponseCode.VALIDATE_ERROR);

        // Xây dựng thông báo lỗi chi tiết
        String listErrorStringBuilder = helper.buildValidationErrorMessage(
                fieldErrors,
                Language.fromValue(lang)
        );

        // Tạo map tham số cho thông báo
        var paramsErrorString = Map.of("errors", listErrorStringBuilder);

        // Tạo thông báo đã được địa phương hóa
        String localizedMessage = helper.buildLocalizedMessage(
                sysMessage,
                lang,
                paramsErrorString
        );

        // Tạo map tham số chi tiết với danh sách lỗi field
        var messParam = Map.of(
                "errors",
                helper.convertFieldErrorsToErrorList(fieldErrors)
        );

        Map<String, String> messLang = helper.getAllTranslations(sysMessage, paramsErrorString);

        // Tạo phản hồi lỗi chuẩn hóa
        Response response = buildErrorResponse(
                sysMessage,
                localizedMessage,
                messParam,
                messLang
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }
}