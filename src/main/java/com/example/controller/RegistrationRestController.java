package com.example.controller;

import com.example.entity.User;
import com.example.form.RegistrationForm;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
First trying to realize registration of new user.
 */
@RestController
@RequestMapping(value = "/register")
public class RegistrationRestController {

    private final UserService userService;

    @Autowired
    public RegistrationRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> register(@RequestBody RegistrationForm registrationForm) {
        User saved = userService.register(registrationForm.composeUser());

        //It may happen if user's username already exists in DB
        if(saved == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

}
