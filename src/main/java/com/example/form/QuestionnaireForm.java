package com.example.form;

import com.example.entity.Questionnaire;
import com.example.entity.User;

public class QuestionnaireForm {

    private String name;
    private String description;
    //TODO field author will be unnecessary after adding security module
    // - DELETE THIS
    private User author;

    public QuestionnaireForm() { }

    /*
    This method parses "QuestionnaireForm" object to common entity "Questionnaire".
    Entity gets name, description and author from QuestionnaireForm object.
    For securing bidirectional relationship between User and Questionnaire entities,
    information about author adds by method addUserCreatedQuestionnaire(Questionnaire q)
    - watch realization in com.example.entity.User.

    WARNING! At this moment, it necessarily to point author of questionnaire (when you
    create new questionnaire from POST request) BY YOUR HANDS! In another way, author of
    questionnaire will be null :( It happens, because the application hasn't security
    module. After security will be installed, only authorized users will be able to
    create questionnaires and field "author" will filling automatically by current
    user, logged in system.

    List of all questions for this questionnaire initialize like empty ArrayList<Question>
    in com.example.entity.Questionnaire;
     */
    public Questionnaire composeQuestionnaire() {
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setName(name);
        questionnaire.setDescription(description);
        author.addUserCreatedQuestionnaire(questionnaire);

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

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
