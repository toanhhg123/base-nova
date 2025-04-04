package com.example.demo.core.security.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.UUID;

@Builder
@Getter
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {
    private final UUID id;
    private final String userId;
    private final String role;
    private final String level;
    private final String department;
    private final String username;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;
}
