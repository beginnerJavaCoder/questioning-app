package com.example.form;

import com.example.entity.User;

public class UserForm {

    private String name;

    /*
    Idk, should I add empty constructor to *Form classes or not.
     */
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

        return user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
