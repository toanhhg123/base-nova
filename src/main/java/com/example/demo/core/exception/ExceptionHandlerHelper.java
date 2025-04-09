package com.example.demo.core.exception;

import com.example.demo.core.constants.Language;
import com.example.demo.core.constants.ResponseCode;
import com.example.demo.core.entities.SysMessage;
import com.example.demo.core.loader.sysmessage.MessageCacheService;
import com.example.demo.core.utils.StringUtils;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.context.request.WebRequest;

import java.util.*;
import java.util.stream.Collectors;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
@Component
@Slf4j
public class ExceptionHandlerHelper {

    private final MessageCacheService messageCacheService;


    /**
     * Gets the language from request header.
     *
     * @param request Web request
     * @return Language code (defaults to Vietnamese)
     */
    public String getLanguageFromRequest(WebRequest request) {
        return Optional.ofNullable(request.getHeader(Language.LANG_HEADER.value))
                .orElse(Language.VI.value);
    }

    /**
     * Gets system message by status message enum.
     *
     * @param statusMessage Status message enum
     * @return System message object
     */
    public SysMessage getSysMessageByStatusMessage(ResponseCode statusMessage) {
        return messageCacheService.getByMessageCode(statusMessage);
    }

    /**
     * Builds a localized message with parameter replacement.
     *
     * @param sysMessage Message template from system
     * @param lang       Target language
     * @param parameters Message parameters
     * @return Formatted message string
     */
    public String buildLocalizedMessage(SysMessage sysMessage, String lang, Map<String, String> parameters) {
        String messageTemplate = sysMessage.getTranslations().get(lang);
        return StringUtils.replacePlaceholders(messageTemplate, parameters);
    }


    /**
     * Gets all available translations for a message with parameters replaced.
     *
     * @param sysMessage System message template
     * @param params     Message parameters
     * @return Map of language codes to translated messages
     */
    public Map<String, String> getAllTranslations(SysMessage sysMessage, Map<String, String> params) {
        String messageVI = sysMessage.getTranslations().getOrDefault(Language.VI.value, "");
        String messageEN = sysMessage.getTranslations().getOrDefault(Language.EN.value, "");

        return Map.of(
                Language.VI.value, StringUtils.replacePlaceholders(messageVI, params),
                Language.EN.value, StringUtils.replacePlaceholders(messageEN, params)
        );
    }


    public List<Map<String, Object>> convertFieldErrorsToErrorList(List<FieldError> fieldErrors) {
        // Group errors by field name
        Map<String, List<String>> groupedErrors = fieldErrors.stream()
                .collect(Collectors.groupingBy(
                        FieldError::getField,
                        Collectors.mapping(FieldError::getDefaultMessage, Collectors.toList())
                ));

        // Convert grouped errors to the desired format
        return groupedErrors.entrySet().stream()
                .map(entry -> {
                    Map<String, Object> errorObj = new HashMap<>();
                    errorObj.put("property", entry.getKey());
                    errorObj.put("message", entry.getValue());
                    return errorObj;
                })
                .toList();
    }


    /**
     * Builds a human-readable validation error message listing all field errors.
     *
     * @param fieldErrors List of field errors from validation
     * @return Formatted error message
     */
    public String buildValidationErrorMessage(List<FieldError> fieldErrors, Language language) {
        StringBuilder messageBuilder = new StringBuilder();
        List<String> errorParts = new ArrayList<>();

        Map<String, List<String>> groupedErrors = fieldErrors.stream()
                .collect(Collectors.groupingBy(
                        FieldError::getField,
                        Collectors.mapping(FieldError::getDefaultMessage, Collectors.toList())
                ));

        groupedErrors.forEach((fieldName, value) -> {
            List<String> errorMessages = value
                    .stream()
                    .map(msg -> fieldName + " - " + messageCacheService.getMessageTranslate(msg, language))
                    .toList();
            errorParts.add(String.join(",", errorMessages));
        });


        messageBuilder.append(String.join(" | ", errorParts));
        return messageBuilder.toString();
    }
}