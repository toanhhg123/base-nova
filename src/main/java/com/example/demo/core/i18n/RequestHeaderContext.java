package com.example.demo.core.i18n;

import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import jakarta.servlet.http.HttpServletRequest;

@Getter
@Component
@RequestScope
public class RequestHeaderContext {
    final String lang;

    public RequestHeaderContext(HttpServletRequest request) {
        this.lang = request.getHeader("lang") != null ? request.getHeader("lang") : "vi";
    }

}