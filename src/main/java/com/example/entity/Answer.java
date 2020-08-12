package com.example.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
@Table(name = "answers")
public class Answer {
    @Id
    @GeneratedValue
    private Integer id;

    private String description;

    /*
    Property that shows, if user chooses this answer to the question, will he be right
     */
    private Boolean correctness;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    @JsonManagedReference
    private Question sourceQuestion;

    public Answer() { }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
