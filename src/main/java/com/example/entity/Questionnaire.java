package com.example.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "questionnaires")
public class Questionnaire extends Model {

    private String name;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User author;

    @OneToMany(mappedBy = "sourceQuestionnaire", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Question> questions;

    public Questionnaire() {
        questions = new ArrayList<>();
    }

    /*
    Method for support bidirectional relationship between Questionnaire and Question.
    It adds question to questionnaire's list of questions and updates info about
    source questionnaire for this question.
     */
    public void addQuestion(Question question) {
        questions.add(question);
        question.setSourceQuestionnaire(this);
    }

    /*
    Method for support bidirectional relationship between Questionnaire and Question.
    It deletes question from questionnaire's list and deletes information about source
    questionnaire for target question.
     */
    public void removeQuestion(Question question) {
        questions.remove(question);
        question.setSourceQuestionnaire(null);
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

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
