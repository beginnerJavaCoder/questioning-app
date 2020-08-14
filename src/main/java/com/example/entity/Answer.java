package com.example.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "answers")
public class Answer extends Model {

    private String description;

    /*
    Property that shows, if user chooses this answer to the question, will he be right
     */
    private Boolean correctness;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    @JsonBackReference
    private Question sourceQuestion;

    public Answer() { }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isCorrect() {
        return correctness;
    }

    public void setCorrectness(Boolean correctness) {
        this.correctness = correctness;
    }

    public Question getSourceQuestion() {
        return sourceQuestion;
    }

    public void setSourceQuestion(Question sourceQuestion) {
        this.sourceQuestion = sourceQuestion;
    }
}
