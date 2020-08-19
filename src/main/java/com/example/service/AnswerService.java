package com.example.service;

import com.example.entity.questionnaire.Answer;
import com.example.entity.questionnaire.Question;

import java.util.List;

public interface AnswerService {

    void create(Answer answer);

    void delete(Integer answerId);

    List<Answer> getAnswersByQuestion(Question question);

    Answer getAnswer(Answer answer);

    Answer getAnswer(Integer answerId);

}
