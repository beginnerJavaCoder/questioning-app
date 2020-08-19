package com.example.controller;

import com.example.entity.questionnaire.Questionnaire;
import com.example.form.QuestionnaireCreationForm;
import com.example.form.QuestionnairePassingForm;
import com.example.service.QuestionnaireService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/questionnaire")
public class QuestionnaireRestController {

    private final QuestionnaireService questionnaireService;
    private final UserService userService;

    @Autowired
    public QuestionnaireRestController(QuestionnaireService questionnaireService, UserService userService) {
        this.questionnaireService = questionnaireService;
        this.userService = userService;
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

    @PostMapping(value = "/{id}/passing")
    public ResponseEntity questionnairePassing(@PathVariable Integer id,
                                               @RequestBody QuestionnairePassingForm form) {
        int quantityOfRightAnswers = questionnaireService.getQuantityOfRightAnswers(id, form.getUserAnswers());
        Map<String, String> response = new HashMap<>();
        response.put("rightAnswers", String.valueOf(quantityOfRightAnswers));

        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Questionnaire> createQuestionnaire(@RequestBody QuestionnaireCreationForm form) {
        Questionnaire questionnaire = form.composeQuestionnaire();
        questionnaireService.create(questionnaire);
        userService.addUserCreatedQuestionnaire(form.getAuthorUsername(), questionnaire);

        return new ResponseEntity<>(questionnaire, HttpStatus.CREATED);
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
