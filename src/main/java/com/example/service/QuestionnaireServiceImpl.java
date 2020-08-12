package com.example.service;

import com.example.entity.Questionnaire;
import com.example.repository.QuestionnaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {

    QuestionnaireRepository questionnaireRepository;

    @Autowired
    public QuestionnaireServiceImpl(QuestionnaireRepository questionnaireRepository) {
        this.questionnaireRepository = questionnaireRepository;
    }

    @Override
    public void create(Questionnaire questionnaire) {
        questionnaireRepository.saveAndFlush(questionnaire);
    }

    @Override
    public void delete(Integer id) {
        questionnaireRepository.deleteById(id);
    }

    @Override
    public List<Questionnaire> getAll() {
        return questionnaireRepository.findAll();
    }

    @Override
    public Questionnaire getQuestionnaire(Integer id) {
        return questionnaireRepository.findById(id).orElse(null);
    }

    @Override
    public Questionnaire getQuestionnaire(Questionnaire questionnaire) {
        return questionnaireRepository.findOne(Example.of(questionnaire)).orElse(null);
    }
}
