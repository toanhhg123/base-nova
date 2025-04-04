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

        /*
         *  "userId": "6666779c47f9866825a80fb1",
         *  "role": "6666769a409bedc9a7a48493",
         *  "level": "66700957b9d9d515cb1e6695",
         *  "department": "6697882f5a693cd199dbd47b",
         *  "iat": 1743650110,
         *  "exp": 1746242110
         * */

        return UserDetailsImpl.builder()
                .id(UUID.randomUUID())
                .userId("6666779c47f9866825a80fb1")
                .username(username)
                .password("password")
                .level("66700957b9d9d515cb1e6695")
                .role("6666769a409bedc9a7a48493")
                .build();
    }
}


