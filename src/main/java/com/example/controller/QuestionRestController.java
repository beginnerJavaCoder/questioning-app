package com.example.controller;

import com.example.entity.Answer;
import com.example.entity.Question;
import com.example.form.AnswerForm;
import com.example.form.QuestionForm;
import com.example.service.QuestionService;
import com.example.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionRestController {

    private QuestionService questionService;
    private QuestionnaireService questionnaireService;

    @Autowired
    public QuestionRestController(QuestionService questionService,
                                  QuestionnaireService questionnaireService) {
        this.questionService = questionService;
        this.questionnaireService = questionnaireService;
    }

    @GetMapping
    public ResponseEntity<List<Question>> getQuestionsByQuestionnaireId(@RequestParam Integer questionnaireId) {
        List<Question> questions = questionService.
                getQuestionsByQuestionnaire(questionnaireService.getQuestionnaire(questionnaireId));

        if (questions == null || questions.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Question> createQuestion(@RequestBody QuestionForm questionForm) {
        Question question = questionForm.composeQuestion();
        questionService.create(question);

        return new ResponseEntity<>(questionService.getQuestion(question), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Question> deleteQuestion(@PathVariable Integer id) {
        Question question = questionService.getQuestion(id);

        if (question == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        questionService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
