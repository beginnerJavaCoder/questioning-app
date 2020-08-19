package com.example.form;

import com.example.entity.Questionnaire;
import com.example.entity.User;

public class QuestionnaireCreationForm {

    private String name;
    private String description;
    /*
    probably, in the future, I will transmit to backend only user's id
     */
    private String authorUsername;

    public QuestionnaireCreationForm() { }

    /*
    This method parses "QuestionnaireForm" object to common entity "Questionnaire".
    Entity gets name, description and author from QuestionnaireForm object.
    For securing bidirectional relationship between User and Questionnaire entities,
    information about author adds by method addUserCreatedQuestionnaire(Questionnaire q)
    - watch realization in com.example.entity.User.

    List of all questions for this questionnaire initialize like empty ArrayList<Question>
    in com.example.entity.Questionnaire;
     */
    public Questionnaire composeQuestionnaire() {
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setName(name);
        questionnaire.setDescription(description);

        return questionnaire;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthorUsername() {
        return authorUsername;
    }

    public void setAuthorUsername(String authorUsername) {
        this.authorUsername = authorUsername;
    }
}
