package com.example.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue
    private Integer id;
    /*
    Description is the wording of the question itself
     */
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "questionnaire_id")
    @JsonBackReference
    private Questionnaire sourceQuestionnaire;

    /*
    Each question contains information about possible answers
    to itself. List "answerOption" represents these answers
     */
    @OneToMany(mappedBy = "sourceQuestion", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Answer> answerOptions;

    public Question() {
        answerOptions = new ArrayList<>();
    }

    /*
    The next 2 methods are same to methods in User and Questionnaire by meaning
     */
    public void addAnswer(Answer answer) {
        answerOptions.add(answer);
        answer.setSourceQuestion(this);
    }

    public void removeAnswer(Answer answer) {
        answerOptions.remove(answer);
        answer.setSourceQuestion(null);
    }

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

    public Questionnaire getSourceQuestionnaire() {
        return sourceQuestionnaire;
    }

    public void setSourceQuestionnaire(Questionnaire sourceQuestionnaire) {
        this.sourceQuestionnaire = sourceQuestionnaire;
    }

    public List<Answer> getAnswerOptions() {
        return answerOptions;
    }

    public void setAnswerOptions(List<Answer> answerOptions) {
        this.answerOptions = answerOptions;
    }
}
