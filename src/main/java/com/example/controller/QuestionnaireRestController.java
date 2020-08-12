package com.example.controller;

import com.example.entity.Questionnaire;
import com.example.form.QuestionnaireForm;
import com.example.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questionnaire")
public class QuestionnaireRestController {

    QuestionnaireService questionnaireService;

    @Autowired
    public QuestionnaireRestController(QuestionnaireService questionnaireService) {
        this.questionnaireService = questionnaireService;
    }

    @GetMapping
    public ResponseEntity<List<Questionnaire>> getAllQuestionnaires() {
        List<Questionnaire> questionnaires = questionnaireService.getAll();

        if (questionnaires == null || questionnaires.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(questionnaires, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Questionnaire> getQuestionnaire(@PathVariable Integer id) {
        Questionnaire questionnaire = questionnaireService.getQuestionnaire(id);

        return questionnaire == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(questionnaire, HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Questionnaire> createQuestionnaire(@RequestBody QuestionnaireForm questionnaireForm) {
        Questionnaire questionnaire = questionnaireForm.composeQuestionnaire();
        questionnaireService.create(questionnaire);

        return new ResponseEntity<>(questionnaireService.getQuestionnaire(questionnaire), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Questionnaire> deleteQuestionnaire(@PathVariable Integer id) {
        Questionnaire questionnaire = questionnaireService.getQuestionnaire(id);

        if (questionnaire == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        questionnaireService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //TODO methods for updating questionnaire info
}
