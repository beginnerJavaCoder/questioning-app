package com.example.service;

import com.example.entity.questionnaire.Question;
import com.example.entity.questionnaire.Questionnaire;

import java.util.List;

public interface QuestionService {

    void create(Question question);

    void delete(Integer questionId);

    List<Question> getQuestionsByQuestionnaire(Questionnaire questionnaire);

    Question getQuestion(Question question);

    Question getQuestion(Integer questionId);
}
