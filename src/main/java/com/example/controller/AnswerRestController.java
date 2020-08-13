package com.example.controller;

import com.example.entity.Answer;
import com.example.form.AnswerForm;
import com.example.service.AnswerService;
import com.example.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/answer")
public class AnswerRestController {

    private AnswerService answerService;
    private QuestionService questionService;

    @Autowired
    public AnswerRestController(AnswerService answerService, QuestionService questionService) {
        this.answerService = answerService;
        this.questionService = questionService;
    }

    @GetMapping
    public ResponseEntity<List<Answer>> getAnswersByQuestionId(@RequestParam Integer questionId) {
        List<Answer> answers = answerService.getAnswersByQuestion(questionService.getQuestion(questionId));

        if (answers == null || answers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(answers, HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Answer> createAnswer(@RequestBody AnswerForm answerForm) {
        Answer answer = answerForm.composeAnswer();
        answerService.create(answer);

        return new ResponseEntity<>(answerService.getAnswer(answer), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Answer> deleteAnswer(@PathVariable Integer id) {
        Answer answer = answerService.getAnswer(id);

        if (answer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        answerService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
