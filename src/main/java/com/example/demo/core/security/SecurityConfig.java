package com.example.demo.core.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

/**
 * Security configuration class to define Spring Security settings.
 */
@Configuration
@EnableWebSecurity // Enables Spring Security for the application.
@EnableMethodSecurity// Enables method-level security annotations like @PreAuthorize.
@RequiredArgsConstructor
public class SecurityConfig {
    private final CustomAuthenticationEntryPoint authEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final CustomJwtDecoder customJwtDecoder;


    /**
     * Configures security settings using the SecurityFilterChain.
     *
     * @param http HttpSecurity instance to configure security rules.
     * @return Configured SecurityFilterChain bean.
     * @throws Exception If an error occurs during configuration.
     */
    @Bean
    public SecurityFilterChain securityFilterChainConfig(HttpSecurity http) throws Exception {

        /* Disable CSRF protection as this is a stateless API */
        http.csrf(AbstractHttpConfigurer::disable);

        /* Enable CORS with custom configuration */
        http.cors(cors -> cors.configurationSource(corsConfigurationSource()));

        /* Define authorization rules */
        http.authorizeHttpRequests(req -> req
                .requestMatchers("/actuator/**").permitAll() // Allow access to actuator endpoints
                .anyRequest().authenticated()); // All other requests must be authenticated

        /* Configure OAuth2 resource server with JWT authentication */
        http.oauth2ResourceServer(oauth2 ->
                oauth2.jwt(jwt -> jwt
                                .decoder(customJwtDecoder)
                                .jwtAuthenticationConverter(new JwtConverter()))
                        .authenticationEntryPoint(authEntryPoint)
                        .accessDeniedHandler(customAccessDeniedHandler)
        );

        /* Set session management to stateless (no sessions will be created or used) */
        http.sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

    /**
     * Defines CORS configuration for the application.
     *
     * @return CorsConfigurationSource with allowed origins, methods, and headers.
     */
    private CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*")); // Allow all origins
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH", "HEAD")); // Allowed HTTP methods
        configuration.setAllowedHeaders(List.of("*")); // Allow all headers
        configuration.setAllowCredentials(false); // Do not allow credentials
        configuration.setMaxAge(3600L); // Cache CORS response for 1 hour

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Apply CORS settings to all endpoints
        return source;
    }
}
