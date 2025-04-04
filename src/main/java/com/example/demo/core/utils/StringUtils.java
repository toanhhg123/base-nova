package com.example.demo.core.utils;

import lombok.experimental.UtilityClass;

import java.util.Map;
import java.util.regex.Pattern;

@UtilityClass
public class StringUtils {

    /**
     * Thay thế các placeholder {key} trong chuỗi bằng giá trị tương ứng từ map
     * @param template Chuỗi template chứa các placeholder cần thay thế
     * @param values Map chứa các cặp key-value để thay thế
     * @return Chuỗi sau khi đã thay thế
     */
    public static String replacePlaceholders(String template, Map<String, String> values) {
        if (template == null || template.isEmpty()) {
            return template;
        }

        if (values == null || values.isEmpty()) {
            return template;
        }

        var pattern = Pattern.compile("\\{([^}]+)}");
        var matcher = pattern.matcher(template);
        var result = new StringBuilder();

        while (matcher.find()) {
            String key = matcher.group(1);
            String replacement = values.getOrDefault(key, matcher.group());
            matcher.appendReplacement(result, replacement);
        }

        matcher.appendTail(result);
        return result.toString();
    }
}
