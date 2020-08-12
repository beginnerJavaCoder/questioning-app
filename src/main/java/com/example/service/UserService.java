package com.example.service;

import com.example.entity.User;

import java.util.List;

public interface UserService {

    void create(User user);

    void delete(Integer userId);

    List<User> getAll();

    User getUser(Integer userId);

    User getUser(User user);

    void renameUser(Integer userId, String newName);
}
