package com.example.repository;

import com.example.entity.Answer;
import com.example.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer> {

    List<Answer> findBySourceQuestion(Question question);
}
