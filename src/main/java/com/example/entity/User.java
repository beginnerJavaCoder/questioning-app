package com.example.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    /*
    name != future login
    name == nickName
     */
    private String name;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Questionnaire> getUserCreatedQuestionnaires() {
        return userCreatedQuestionnaires;
    }

    public void setUserCreatedQuestionnaires(List<Questionnaire> userCreatedQuestionnaires) {
        this.userCreatedQuestionnaires = userCreatedQuestionnaires;
    }
}
