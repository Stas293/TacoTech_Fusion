package com.spring.tacospring.model;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserInformation extends UserDetails {
    Long getId();
    String getFullname();
    String getPhoneNumber();
    String getEmail();
}
