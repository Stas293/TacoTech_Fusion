package com.spring.tacospring.config;

import com.spring.tacospring.model.AddressInformation;
import com.spring.tacospring.model.User;
import com.spring.tacospring.model.UserInformation;
import com.spring.tacospring.service.ExtendedUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.SecurityFilterChain;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig {
    private static final String LOGIN_PAGE = "/login";
    private static final String LOGOUT_URL = "/logout";
    private static final String DEFAULT_SUCCESS_URL = "/";

    private final ExtendedUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(urlConfig -> urlConfig
                        .requestMatchers("/login", "/register", "/css/styles.css").permitAll()
                        .anyRequest().authenticated()
                )
                .logout(logout -> logout
                        .logoutUrl(LOGOUT_URL)
                        .logoutSuccessUrl(LOGIN_PAGE)
                )
                .formLogin(login -> login
                        .loginPage(LOGIN_PAGE)
                        .defaultSuccessUrl(DEFAULT_SUCCESS_URL)
                )
                .oauth2Login(config -> config
                        .loginPage(LOGIN_PAGE)
                        .defaultSuccessUrl(DEFAULT_SUCCESS_URL)
                        .userInfoEndpoint(userInfo -> userInfo
                                .oidcUserService(oidcUserService())
                        )
                )
                .build();
    }

    @Bean
    public OAuth2UserService<OidcUserRequest, OidcUser> oidcUserService() {
        return new OidcUserService() {
            @Override
            public OidcUser loadUser(OidcUserRequest userRequest) {
                OidcIdToken idToken = userRequest.getIdToken();
                String email = idToken.getClaim("email");
                if (email == null) {
                    handleMissingEmail();
                }
                UserDetails userDetails = userDetailsService.loadUserByEmail(email);
                return createProxyUser(userDetails, userRequest);
            }
        };
    }

    private void handleMissingEmail() {
        log.error("Email is missing in the ID token");
        throw new UsernameNotFoundException("Email is missing in the ID token");
    }

    private OidcUser createProxyUser(UserDetails userDetails, OidcUserRequest userRequest) {
        DefaultOidcUser defaultOidcUser = new DefaultOidcUser(
                userDetails.getAuthorities(), userRequest.getIdToken());
        Set<Method> userMethods = getUserMethods();
        return (OidcUser) Proxy.newProxyInstance(
                SecurityConfig.class.getClassLoader(),
                new Class[]{UserInformation.class, AddressInformation.class, OidcUser.class},
                (proxy, method, args) -> {
                    if (userMethods.contains(method)) {
                        return method.invoke(userDetails, args);
                    }
                    return method.invoke(defaultOidcUser, args);
                });
    }

    private Set<Method> getUserMethods() {
        return Stream.of(User.class.getInterfaces())
                .map(Class::getMethods)
                .flatMap(Arrays::stream)
                .collect(Collectors.toSet());
    }
}
