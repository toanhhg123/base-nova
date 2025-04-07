package com.example.demo.core.security;

import com.example.demo.core.security.user.UserDetailsImpl;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.*;
import java.util.stream.Collectors;


@SuppressWarnings("unchecked")
public class JwtConverter implements Converter<Jwt, AbstractAuthenticationToken> {
    private static final String ROLE_PREFIX = "ROLE_";
    private static final String ROLES_KEY = "roles";
    private static final String USERNAME_KEY = "preferred_username";
    private static final String REALM_ACCESS_CLAIM = "realm_access";
    private static final String RESOURCE_ACCESS_CLAIM = "resource_access";

    /**
     * var user = UserDetailsImplKeyCloak.builder()
     * .authorities(authorities)
     * .username(getUsername(source))
     * .id(source.getClaimAsString("sub"))
     * .claims(source.getClaims())
     * .build();
     */
    @Override
    public AbstractAuthenticationToken convert(@NonNull Jwt source) {
        Collection<GrantedAuthority> authorities = extractAuthorities(source);
        var sub = source.getSubject();

        var user = UserDetailsImpl.builder()
                .id(UUID.fromString(sub))
                .userId(source.getClaimAsString("userId"))
                .level(source.getClaimAsString("level"))
                .role(source.getClaimAsString("role"))
                .build();

        var token = new JwtAuthenticationToken(source, authorities);
        token.setDetails(user);
        return token;
    }

    private String getUsername(Jwt source) {
        return source.getClaimAsString(USERNAME_KEY);
    }

    private Collection<GrantedAuthority> extractAuthorities(Jwt jwt) {
        Set<String> roles = new HashSet<>();

        // Extract roles from realm_access
        extractRealmRoles(jwt, roles);

        // Extract roles from resource_access
        extractResourceRoles(jwt, roles);

        // Convert roles to authorities
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(ROLE_PREFIX + role))
                .collect(Collectors.toSet());
    }


    private void extractRealmRoles(Jwt jwt, Set<String> roles) {
        Map<String, Object> realmAccess = jwt.getClaim(REALM_ACCESS_CLAIM);
        if (realmAccess != null && realmAccess.containsKey(ROLES_KEY)) {
            List<String> realmRoles = (List<String>) realmAccess.get(ROLES_KEY);
            roles.addAll(realmRoles);
        }
    }

    private void extractResourceRoles(Jwt jwt, Set<String> roles) {
        Map<String, Object> resourceAccess = jwt.getClaim(RESOURCE_ACCESS_CLAIM);
        if (resourceAccess == null) {
            return;
        }

        resourceAccess.forEach((key, value) -> {
            if (!(value instanceof Map)) return;

            Map<String, Object> resource = (Map<String, Object>) value;
            if (resource.containsKey(ROLES_KEY)) {
                List<String> resourceRoles = (List<String>) resource.get(ROLES_KEY);
                roles.addAll(resourceRoles);
            }

        });
    }
}
