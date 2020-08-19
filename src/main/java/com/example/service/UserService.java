package com.example.service;

import com.example.entity.questionnaire.Questionnaire;
import com.example.entity.user.User;

import java.util.List;

public interface UserService {

    void addUserCreatedQuestionnaire(String username, Questionnaire questionnaire);

    User register(User user);

    void delete(Integer userId);

    List<User> getAll();

    User getUser(Integer userId);

    User getUser(User user);

    boolean isUsernameAlreadyExists(String username);

    User findByUsername(String username);

    void renameUser(Integer userId, String newName);
}
