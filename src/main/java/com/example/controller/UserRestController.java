package com.example.controller;

import com.example.entity.User;
import com.example.form.UserForm;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/*
TODO: some of logic must be allowed only for admin panel, some for all users
 */
@RestController
@RequestMapping("/user")
public class UserRestController {

    private UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAll();

        if (users == null || users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id) {
        User user = userService.getUser(id);

        return user == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Integer id) {
        User user = userService.getUser(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        userService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping(value = "/rename/{id}")
    public ResponseEntity<User> renameUser(@PathVariable Integer id,
                                           @RequestBody UserForm userForm) {

        if (userService.getUser(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        userService.renameUser(id, userForm.getName());

        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }

    //TODO: controller for filling userCreatedQuestionnaires
}
