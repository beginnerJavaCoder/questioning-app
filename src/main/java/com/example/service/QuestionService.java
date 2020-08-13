package com.example.service;

import com.example.entity.Question;
import com.example.entity.Questionnaire;

import java.util.List;

public interface QuestionService {

    void create(Question question);

    void delete(Integer questionId);

    List<Question> getQuestionsByQuestionnaire(Questionnaire questionnaire);

    Question getQuestion(Question question);

    Question getQuestion(Integer questionId);
}
