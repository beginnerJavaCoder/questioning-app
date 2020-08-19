package com.example.service;

import com.example.entity.Questionnaire;

import java.util.List;

public interface QuestionnaireService {

    int getQuantityOfRightAnswers(int QuestionnaireId, boolean[][] userAnswers);

    void create(Questionnaire questionnaire);

    void delete(Integer id);

    List<Questionnaire> getAll();

    Questionnaire getQuestionnaire(Integer id);

    Questionnaire getQuestionnaire(Questionnaire questionnaire);

    //TODO update methods
}
