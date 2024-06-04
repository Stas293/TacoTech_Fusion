package com.spring.tacospring.config;

import com.spring.tacospring.data.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepo) {
        return username -> userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("User not found by name: %s", username)
                ));
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(urlConfig -> urlConfig
                        .requestMatchers("/login", "/register", "/css/styles.css").permitAll()
                        .requestMatchers("/orders/**", "/design/**").access(
                                new WebExpressionAuthorizationManager("hasRole('USER') && " +
                                        "T(java.time.LocalTime).now().isAfter(T(java.time.LocalTime).of(8, 0)) && " +
                                        "T(java.time.LocalTime).now().isBefore(T(java.time.LocalTime).of(20, 0))")
                        )
                        .anyRequest().authenticated()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .deleteCookies("JSESSIONID")
                )
                .formLogin(login -> login
                        .loginPage("/login")
                        .defaultSuccessUrl("/")
                )
                .build();
    }
}
