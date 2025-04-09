package com.example.demo.core.exception.handler;

import com.example.demo.core.constants.Language;
import com.example.demo.core.constants.ResponseCode;
import com.example.demo.core.constants.ResponseStatus;
import com.example.demo.core.dto.model.Response;
import com.example.demo.core.exception.ExceptionHandlerHelper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
@RestControllerAdvice
@Slf4j
public class ValidationExceptionHandler {

    ExceptionHandlerHelper helper;

    /**
     * Handles validation exceptions from request body validation.
     *
     * @param ex      Validation exception
     * @param request Current web request
     * @return ResponseEntity containing validation error details
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response> handleValidationExceptions(
            MethodArgumentNotValidException ex,
            WebRequest request) {

        // Get language from request header
        String lang = helper.getLanguageFromRequest(request);

        // Extract field errors
        var fieldErrors = ex.getBindingResult().getFieldErrors();


        // Get system message for validation errors
        var sysMessage = helper.getSysMessageByStatusMessage(ResponseCode.VALIDATE_ERROR);

        // Build localized message
        String listErrorStringBuilder = helper.buildValidationErrorMessage(fieldErrors, Language.fromValue(lang));
        var pramsErrorString = Map.of("errors", listErrorStringBuilder);
        String localizedMessage = helper.buildLocalizedMessage(sysMessage, lang, pramsErrorString);

        // Get detailed field errors
        var messParam = Map.of(
                "errors",
                helper.convertFieldErrorsToErrorList(fieldErrors)
        );


        // Build standard error response with details
        Response response = Response.builder()
                .status(ResponseStatus.ERROR)
                .statusCode(ResponseCode.VALIDATE_ERROR.statusCode.value())
                .message(localizedMessage)
                .messCode(sysMessage.getMessageCode())
                .messSeq(sysMessage.getMessageSeq())
                .messParam(messParam)
                .messLang(helper.getAllTranslations(sysMessage, pramsErrorString))
                .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }


}