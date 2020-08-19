package com.example.form;

import com.example.entity.user.User;

//TODO think about deletion this class
public class UserForm {

    private String name;
    private String username;
    private String password;

    public UserForm() { }

    /*
    This method parses "UserForm" object to normal entity "User".
    At this moment, creating entity gets only name from object UserForm.
    Hibernate provides @id. And list of all questionnaires, created by this user,
    initializing as empty ArrayList<Questionnaire>.
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
