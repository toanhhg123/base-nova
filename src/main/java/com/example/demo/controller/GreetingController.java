package com.example.demo.controller;

import com.example.demo.core.entities.SysMessage;
import com.example.demo.core.i18n.MessageService;
import com.example.demo.core.i18n.messages.MessageI18n;
import com.example.demo.core.repositories.SysMessageRepository;
import com.example.demo.core.security.user.UserDetailsImplKeyCloak;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.SpanContext;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@AllArgsConstructor
@Slf4j
public class GreetingController {
    private final MessageService messageService;
    private final SysMessageRepository sysMessageRepository;

    @PostMapping("/tracking-info")
    @PreAuthorize("hasRole('APP_USER')")
    public SpanContext getTrackingInfo() {
        Span span = Span.current();
        return span.getSpanContext();
    }

    @GetMapping("/message")
    public List<SysMessage> getMessages() {
        return sysMessageRepository.findAll();
    }


    @GetMapping("/create-message")
    public SysMessage createMessage() {

        SysMessage sysMessage = new SysMessage();
        sysMessage.setMessageCode("test");
        sysMessage.setMessageSeq("test");
        sysMessage.setStatusCode(200);
        sysMessage.setTranslations(
                Map.of(
                        "en", "test",
                        "vi", "test"
                )
        );
        return sysMessageRepository.save(sysMessage);
    }

    @DeleteMapping("/message/{id}")
    public SysMessage deleteMessage(@PathVariable() UUID id) {
        var message = sysMessageRepository.findById(id)
                .orElseThrow();
        sysMessageRepository.delete(message);
        return message;
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
