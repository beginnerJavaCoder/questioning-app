package com.example.form;

import com.example.entity.user.User;

public class RegistrationForm {

    private String name;

    private String username;
    private String password;

    public RegistrationForm() { }

    /*
    This method parses "RegistrationForm" object to normal entity "User".
     */
    public User composeUser() {
        User user = new User();
        user.setName(name);
        user.setUsername(username);
        user.setPassword(password);

        return user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
