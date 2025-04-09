package com.example.demo.core.security.service;

import com.example.demo.core.security.user.UserDetailsImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // fake user authentication
        return UserDetailsImpl.builder()
                .id(UUID.fromString("7100d653-dfb9-436b-a9e6-a5677d93a194"))
                .userId("6666779c47f9866825a80fb1")
                .username(username)
                .password("password")
                .level("66700957b9d9d515cb1e6695")
                .role("6666769a409bedc9a7a48493")
                .build();
    }
}


