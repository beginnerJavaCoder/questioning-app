package com.example.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends Model {

    /*
    Username/nickname which will be visible to other users of the app
     */
    private String name;
    /*
    Unique credential that in common way may be user's email
     */
    private String login;
    /*
    TODO: Take care about this field won't store open in DB
     */
    private String password;

    /*
    This list contains all privileges of the user
     */
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = {@JoinColumn(name = "user_id")})
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    /*
    Property CascadeType.PERSIST instead CascadeType.ALL uses,
    because after deleting user from system, it maybe useful to
    save questionnaires created by him for possibility to pass
    these questionnaires by another users of the system.
     */
    @OneToMany(mappedBy = "author", cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private List<Questionnaire> userCreatedQuestionnaires;

    /*
    Is this a bad practice? If yes, where can I initialize lists in entities?
     */
    public User() {
        userCreatedQuestionnaires = new ArrayList<>();
        roles = new HashSet<>();
        roles.add(Role.USER);
    }

    /*
    Method for support bidirectional relationship between User and Questionnaire.
    It adds questionnaire to user's list of questionnaires and updates info about
    author in the questionnaire.
     */
    public void addUserCreatedQuestionnaire(Questionnaire questionnaire) {
        userCreatedQuestionnaires.add(questionnaire);
        questionnaire.setAuthor(this);
    }

    /*
    Method for support bidirectional relationship between User and Questionnaire.
    It deletes questionnaire from user's list and deletes information about author
    in target questionnaire.
     */
    public void removeUserCreatedQuestionnaire(Questionnaire questionnaire) {
        userCreatedQuestionnaires.remove(questionnaire);
        questionnaire.setAuthor(null);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public List<Questionnaire> getUserCreatedQuestionnaires() {
        return userCreatedQuestionnaires;
    }

    public void setUserCreatedQuestionnaires(List<Questionnaire> userCreatedQuestionnaires) {
        this.userCreatedQuestionnaires = userCreatedQuestionnaires;
    }
}
