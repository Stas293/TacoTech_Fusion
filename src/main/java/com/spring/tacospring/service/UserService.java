package com.spring.tacospring.service;

import com.spring.tacospring.data.UserRepository;
import com.spring.tacospring.dto.UserRegistrationDTO;
import com.spring.tacospring.mapper.UserRegistrationMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements ExtendedUserDetailsService {
    private final UserRepository userRepository;
    private final UserRegistrationMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("User not found by name: %s", username)
                ));
    }

    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("User not found by email: %s", email)
                ));
    }

    public boolean register(UserRegistrationDTO user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            return false;
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            return false;
        }
        userRepository.save(mapper.toEntity(user));
        return true;
    }
}
