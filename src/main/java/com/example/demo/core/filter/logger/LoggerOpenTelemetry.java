package com.example.demo.core.filter.logger;

import io.opentelemetry.api.trace.Span;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;


@Component
@Slf4j
public class LoggerOpenTelemetry implements Filter {
    private static final String REQUEST_BODY = "http.request.body";
    private static final String RESPONSE_BODY = "http.response.body";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (!(request instanceof HttpServletRequest) || !(response instanceof HttpServletResponse)) {
            chain.doFilter(request, response);
            return;
        }

        CachedRequestWrapper requestWrapper = new CachedRequestWrapper((HttpServletRequest) request);
        CachedResponseWrapper responseWrapper = new CachedResponseWrapper((HttpServletResponse) response);


        Span span = Span.current();
        String requestBody = requestWrapper.getBody();
        span.setAttribute(REQUEST_BODY, requestBody);

        chain.doFilter(requestWrapper, responseWrapper);

        String responseBody = responseWrapper.getResponseBody();
        span.setAttribute(RESPONSE_BODY, responseBody);

        response.getOutputStream().write(responseBody.getBytes(StandardCharsets.UTF_8));
        response.getOutputStream().flush();
    }
}
