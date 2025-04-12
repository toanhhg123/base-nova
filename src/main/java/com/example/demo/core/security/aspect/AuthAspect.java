package com.example.demo.core.security.aspect;

import com.example.demo.core.security.annotation.Auth;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Aspect dùng để xử lý annotation @Auth tại class hoặc method.
 * Mục đích: kiểm tra quyền truy cập trước khi thực hiện logic controller.
 */
@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class AuthAspect {

    /**
     * Điểm cắt AOP: áp dụng cho mọi method có gắn @Auth hoặc class có @Auth.
     * Sử dụng `@within` để bắt @Auth gắn ở class,
     * và `@annotation` để bắt @Auth gắn trực tiếp ở method.
     */
    @Before("""
                @within(com.example.demo.core.security.annotation.Auth) ||
                @annotation(com.example.demo.core.security.annotation.Auth)
            """)
    public void checkPermission(JoinPoint joinPoint) {
        // Lấy thông tin người dùng đang đăng nhập từ SecurityContext (chứa token đã parse)
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        // Lấy metadata của method hiện tại
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        // Lấy class nơi method đang được gọi
        Class<?> targetClass = joinPoint.getTarget().getClass();

        // Tìm annotation @Auth nếu có gắn ở class
        var classAuth = targetClass.getAnnotation(Auth.class);

        // Tìm annotation @Auth nếu có gắn ở method
        var methodAuth = method.getAnnotation(Auth.class);

        // Ghi log thông tin để debug kiểm tra (nếu cần)
        log.info("checkPermission: {}", authentication.getDetails());
        log.info("checkPermission: {} , {}", classAuth, methodAuth);


    }
}
