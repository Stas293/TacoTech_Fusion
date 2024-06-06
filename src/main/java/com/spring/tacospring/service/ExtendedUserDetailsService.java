package com.spring.tacospring.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface ExtendedUserDetailsService extends UserDetailsService {
    UserDetails loadUserByEmail(String email) throws UsernameNotFoundException;
}
