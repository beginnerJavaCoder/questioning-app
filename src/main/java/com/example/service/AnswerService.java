package com.example.service;

import com.example.entity.Answer;
import com.example.entity.Question;

import java.util.List;

public interface AnswerService {

    void create(Answer answer);

    void delete(Integer answerId);

    List<Answer> getAnswersByQuestion(Question question);

    Answer getAnswer(Answer answer);

    Answer getAnswer(Integer answerId);

}
