package com.example.entity.user;

import com.example.entity.Model;
import com.example.entity.questionnaire.Questionnaire;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends Model implements UserDetails {

    /*
    Nickname which will be visible to other users of the app
     */
    private String name;
    /*
    Unique credential that in common way may be user's email
     */
    private String username;
    /*
    password will be saved in DB in hashed form
     */
    private String password;
    /*
    Property that shows, in which state account is now.
    By default, each account creating with status ACTIVE.
    Deletion of account changes its status on DELETED,
    but all information in DB remains intact.
     */
    @Enumerated(EnumType.STRING)
    private Status status;

    /*
    This list contains all privileges of the user
     */
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = {@JoinColumn(name = "user_id")})
    @Enumerated(EnumType.STRING)
    private List<Role> roles;

    /*
    Property CascadeType.PERSIST instead CascadeType.ALL uses,
    because after deleting user from system, it maybe useful to
    save questionnaires created by him for possibility to pass
    these questionnaires by another users of the system.
     */
    @OneToMany(mappedBy = "author", cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private List<Questionnaire> userCreatedQuestionnaires;

    public User() {
        status = Status.ACTIVE;
        roles = new ArrayList<>();
        roles.add(Role.USER);
        userCreatedQuestionnaires = new ArrayList<>();
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

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return status == Status.ACTIVE;
    }

    /*
    Realization isn't provided
     */
    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    /*
    Realization isn't provided
     */
    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    /*
    Realization isn't provided
    */
    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public List<Role> getAuthorities() {
        return roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Questionnaire> getUserCreatedQuestionnaires() {
        return userCreatedQuestionnaires;
    }

    public void setUserCreatedQuestionnaires(List<Questionnaire> userCreatedQuestionnaires) {
        this.userCreatedQuestionnaires = userCreatedQuestionnaires;
    }
}
