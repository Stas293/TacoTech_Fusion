package com.spring.tacospring.mapper;

import com.spring.tacospring.dto.UserRegistrationDTO;
import com.spring.tacospring.model.User;
import lombok.SneakyThrows;
import org.mapstruct.*;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserRegistrationMapper {
    User toEntity(UserRegistrationDTO userRegistrationDTO);

    UserRegistrationDTO toDto(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User partialUpdate(UserRegistrationDTO userRegistrationDTO, @MappingTarget User user);

    @AfterMapping
    @SneakyThrows
    default void trimFields(@MappingTarget User user) {
        BeanInfo beanInfo = Introspector.getBeanInfo(User.class);
        for (PropertyDescriptor pd : beanInfo.getPropertyDescriptors()) {
            if (pd.getPropertyType().equals(String.class)) {
                Method getter = pd.getReadMethod();
                Method setter = pd.getWriteMethod();
                if (getter != null && setter != null) {
                    String value = (String) getter.invoke(user);
                    if (value != null) {
                        setter.invoke(user, value.trim());
                    }
                }
            }
        }
    }
}