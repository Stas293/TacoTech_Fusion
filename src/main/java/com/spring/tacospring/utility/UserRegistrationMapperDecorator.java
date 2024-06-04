package com.spring.tacospring.utility;

import com.spring.tacospring.dto.UserRegistrationDTO;
import com.spring.tacospring.mapper.UserRegistrationMapper;
import com.spring.tacospring.model.User;
import lombok.experimental.Delegate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Primary
public class UserRegistrationMapperDecorator implements UserRegistrationMapper {
    private final PasswordEncoder passwordEncoder;
    @Delegate
    private final UserRegistrationMapper delegate;

    public UserRegistrationMapperDecorator(PasswordEncoder passwordEncoder,
                                           @Qualifier("userRegistrationMapperImpl") UserRegistrationMapper delegate) {
        this.passwordEncoder = passwordEncoder;
        this.delegate = delegate;
    }

    @Override
    public User toEntity(UserRegistrationDTO userRegistrationDTO) {
        User user = delegate.toEntity(userRegistrationDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return user;
    }
}
