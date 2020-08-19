package com.example.repository;

import com.example.entity.questionnaire.Question;
import com.example.entity.questionnaire.Questionnaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    List<Question> findBySourceQuestionnaire(Questionnaire questionnaire);
}
