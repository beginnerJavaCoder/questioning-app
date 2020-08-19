package com.example.service;

import com.example.entity.questionnaire.Questionnaire;
import com.example.entity.user.Status;
import com.example.entity.user.User;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    /*
    passwordEncoder used for hashing passwords those will be saved in the DB.
    It means that passwords in their raw form will not be saved in the database.
     */
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void addUserCreatedQuestionnaire(String username, Questionnaire questionnaire) {
        User author = findByUsername(username);
        author.addUserCreatedQuestionnaire(questionnaire);
        userRepository.save(author);
    }

    /*
        Checks if user with user.username already exists. In this case returns null.
        If db has no user with such a username, then hashes user's password and saves user into db.
        Returns saved user.
         */
    @Override
    public User register(User user) {
        if(isUsernameAlreadyExists(user.getUsername())) {
            return null;
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.saveAndFlush(user);
    }

    @Override
    public boolean isUsernameAlreadyExists(String username) {
        return this.findByUsername(username) != null;
    }

    @Override
    public User getUser(User user) {
        return userRepository.findOne(Example.of(user)).orElse(null);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
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

    /*
    Deletion of an user not delete any his information,
    because it may be valuable for statistics or user account's recovery
     */
    @Override
    public void delete(Integer userId) {
        User candidateToRemove = this.getUser(userId);
        candidateToRemove.setStatus(Status.DELETED);
        userRepository.save(candidateToRemove);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }
}
