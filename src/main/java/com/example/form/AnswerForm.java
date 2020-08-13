package com.example.form;

import com.example.entity.Answer;
import com.example.entity.Question;

public class AnswerForm {

    private String description;
    private Boolean correctness;
    //TODO questionSource field will be unnecessary, when we will know in which question context we are working
    // - DELETE THIS
    private Question questionSource;

    public AnswerForm() { }

    public Answer composeAnswer() {
        Answer answer = new Answer();
        answer.setDescription(description);
        answer.setCorrectness(correctness);
        questionSource.addAnswer(answer);

        return answer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getCorrectness() {
        return correctness;
    }

    public void setCorrectness(Boolean correctness) {
        this.correctness = correctness;
    }

    public Question getQuestionSource() {
        return questionSource;
    }

    public void setQuestionSource(Question questionSource) {
        this.questionSource = questionSource;
    }
}
