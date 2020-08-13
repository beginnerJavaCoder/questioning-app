package com.example.service;

import com.example.entity.Questionnaire;
import com.example.entity.User;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void create(User user) {
        userRepository.saveAndFlush(user);
    }

    @Override
    public User getUser(User user) {
        return userRepository.findOne(Example.of(user)).orElse(null);
    }

    @Override
    public User getUser(Integer userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public void renameUser(Integer userId, String newName) {
        User current = userRepository.getOne(userId);
        current.setName(newName);
        userRepository.saveAndFlush(current);
    }

    @Override
    public void delete(Integer userId) {
        User candidateToRemove = this.getUser(userId);
        List<Questionnaire> questionnaires = candidateToRemove.getUserCreatedQuestionnaires();
        if (questionnaires != null) {
            for (Questionnaire q : questionnaires) {
                q.setAuthor(null);
            }
        }
        
        userRepository.deleteById(userId);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }
}
