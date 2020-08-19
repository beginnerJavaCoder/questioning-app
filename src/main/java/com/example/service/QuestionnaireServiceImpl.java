package com.example.service;

import com.example.entity.questionnaire.Answer;
import com.example.entity.questionnaire.Question;
import com.example.entity.questionnaire.Questionnaire;
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
    public int getQuantityOfRightAnswers(int questionnaireId, boolean[][] userAnswers) {
        Questionnaire questionnaire = getQuestionnaire(questionnaireId);
        List<Question> questions = questionnaire.getQuestions();
        int quantityOfRightAnswers = questions.size();

        for (int i = 0; i < userAnswers.length; i++) {
            List<Answer> answerOptions = questions.get(i).getAnswerOptions();
            for (int j = 0; j < userAnswers[i].length; j++) {
                if (answerOptions.get(j).isCorrect() != userAnswers[i][j]) {
                    quantityOfRightAnswers--;
                    break;
                }
            }
        }

        return quantityOfRightAnswers;
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
