package com.example.form;

import com.example.entity.Answer;
import com.example.entity.Question;
import com.example.entity.Questionnaire;

import java.util.List;

public class QuestionForm {

    private String description;
    //TODO sourceQuestionnaire field will be unnecessary,
    // when we will know in which questionnaire context we are working
    // - DELETE THIS
    private Questionnaire sourceQuestionnaire;

    public QuestionForm() { }

    public Question composeQuestion() {
        Question question = new Question();
        question.setDescription(description);
        sourceQuestionnaire.addQuestion(question);

        return question;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Questionnaire getSourceQuestionnaire() {
        return sourceQuestionnaire;
    }

    public void setSourceQuestionnaire(Questionnaire sourceQuestionnaire) {
        this.sourceQuestionnaire = sourceQuestionnaire;
    }

}
