package com.example.demo.core.security.user;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Map;


@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserDetailsImplKeyCloak implements UserDetails {
    String id;
    String username;
    transient Collection<GrantedAuthority> authorities;
    transient Map<String, Object> claims;

    @Override
    public String getPassword() {
        return null;
    }
}
