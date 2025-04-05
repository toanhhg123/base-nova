package com.example.demo.controller;

import com.example.demo.core.i18n.MessageService;
import com.example.demo.core.i18n.messages.MessageI18n;
import com.example.demo.core.repositories.SysMessageRepository;
import com.example.demo.core.security.user.UserDetailsImplKeyCloak;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.SpanContext;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@AllArgsConstructor
@Slf4j
public class GreetingController {
    MessageService messageService;
    SysMessageRepository sysMessageRepository;

    @PostMapping("/tracking-info")
    @PreAuthorize("hasRole('APP_USER')")
    public SpanContext getTrackingInfo() {
        Span span = Span.current();
        return span.getSpanContext();
    }

    @GetMapping("/me")
    public UserDetailsImplKeyCloak getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AccessDeniedException("User not authenticated");
        }

        return (UserDetailsImplKeyCloak) authentication.getDetails();
    }


    @GetMapping("/greet")
    public String greet() {
        return messageService.getMessage(MessageI18n.HELLO);
    }
}
