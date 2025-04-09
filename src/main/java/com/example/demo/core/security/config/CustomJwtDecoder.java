package com.example.demo.core.security.config;

import com.example.demo.core.constants.JwtConfigProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

@Slf4j
@Component
@AllArgsConstructor
public class CustomJwtDecoder implements JwtDecoder {
    private final JwtConfigProperties jwtConfig;

    @Override
    public Jwt decode(String token) throws JwtException {
        byte[] secretKeyBytes = jwtConfig.getSecretKey().getBytes(StandardCharsets.UTF_8);
        var secretKey = new SecretKeySpec(secretKeyBytes, "HS256");

        // Tạo decoder
        NimbusJwtDecoder decoder = NimbusJwtDecoder
                .withSecretKey(secretKey)
                .build();

        // Sử dụng decoder để decode token
        return decoder.decode(token);
    }
}
