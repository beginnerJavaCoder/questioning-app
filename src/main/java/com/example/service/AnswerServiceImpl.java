package com.example.service;

import com.example.entity.questionnaire.Answer;
import com.example.entity.questionnaire.Question;
import com.example.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {

    AnswerRepository answerRepository;

    @Autowired
    public AnswerServiceImpl(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }


    @Override
    public void create(Answer answer) {
        answerRepository.saveAndFlush(answer);
    }

    @Override
    public void delete(Integer answerId) {
        answerRepository.deleteById(answerId);
    }

    @Override
    public List<Answer> getAnswersByQuestion(Question question) {
        return answerRepository.findBySourceQuestion(question);
    }

    @Override
    public Answer getAnswer(Answer answer) {
        return answerRepository.findOne(Example.of(answer)).orElse(null);
    }

    @Override
    public Answer getAnswer(Integer answerId) {
        return answerRepository.findById(answerId).orElse(null);
    }
}
