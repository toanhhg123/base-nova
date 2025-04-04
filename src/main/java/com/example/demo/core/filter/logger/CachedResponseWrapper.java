package com.example.demo.core.filter.logger;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.WriteListener;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class CachedResponseWrapper extends HttpServletResponseWrapper {
    private final ByteArrayOutputStream cachedOutputStream = new ByteArrayOutputStream();
    private final ServletOutputStream outputStream;
    private PrintWriter writer;

    public CachedResponseWrapper(HttpServletResponse response) {
        super(response);
        outputStream = new ServletOutputStream() {
            @Override
            public void write(int b) throws IOException {
                cachedOutputStream.write(b);
            }

            @Override
            public boolean isReady() {
                return true;
            }

            @Override
            public void setWriteListener(WriteListener writeListener) {
                // Không cần triển khai
            }
        };
    }

    @Override
    public ServletOutputStream getOutputStream() {
        return outputStream;
    }

    @Override
    public PrintWriter getWriter() {
        if (writer == null) {
            writer = new PrintWriter(outputStream, true, StandardCharsets.UTF_8);
        }
        return writer;
    }

    public String getResponseBody() {
        return cachedOutputStream.toString(StandardCharsets.UTF_8);
    }
}