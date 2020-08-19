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
    UserService userService;

    @Autowired
    public QuestionnaireServiceImpl(QuestionnaireRepository questionnaireRepository,
                                    UserService userService) {
        this.questionnaireRepository = questionnaireRepository;
        this.userService = userService;
    }

    /*
    Calculates quantity of right answers, then uses userService for
    calculating of experience increasing.
     */
    @Override
    public int getQuantityOfRightAnswers(int questionnaireId, boolean[][] userAnswers, int userId) {
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

        userService.increaseExperience(userId, calculateUserExperienceGain(questions.size(), quantityOfRightAnswers));

        return quantityOfRightAnswers;
    }

    /*
    For each questionnaire user may increase his experience by 10 points,
    if he answers at all questions right.
     */
    private int calculateUserExperienceGain(double questionsCount, double rightAnswersCount) {
        return (int) ((rightAnswersCount / questionsCount) * 10);
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
