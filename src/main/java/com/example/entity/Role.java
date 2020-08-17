package com.example.entity;

import org.springframework.security.core.GrantedAuthority;

/*
This enum defines, which actions allow to user of our app.
User can have more than one role in the same time.
Role USER allows you to complete questionnaires created by other users.
Role CREATOR allows you to create new questionnaires in the app.
Role ADMIN allows you to get access to administrator panel with tasty features.
 */
public enum Role implements GrantedAuthority {
    ADMIN,
    CREATOR,
    USER;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
