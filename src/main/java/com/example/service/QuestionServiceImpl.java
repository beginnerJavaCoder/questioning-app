package com.example.service;

import com.example.entity.Question;
import com.example.entity.Questionnaire;
import com.example.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    QuestionRepository questionRepository;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }


    @Override
    public void create(Question question) {
        questionRepository.saveAndFlush(question);
    }

    @Override
    public void delete(Integer questionId) {
        questionRepository.deleteById(questionId);
    }

    @Override
    public List<Question> getQuestionsByQuestionnaire(Questionnaire questionnaire) {
        return questionRepository.findBySourceQuestionnaire(questionnaire);
    }

    @Override
    public Question getQuestion(Question question) {
        return questionRepository.findOne(Example.of(question)).orElse(null);
    }

    @Override
    public Question getQuestion(Integer questionId) {
        return questionRepository.findById(questionId).orElse(null);
    }
}
