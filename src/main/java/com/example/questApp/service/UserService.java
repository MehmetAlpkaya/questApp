package com.example.questApp.service;

import com.example.questApp.entity.User;
import com.example.questApp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService
{
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveUser(User newUser) {
        return userRepository.save(newUser);
    }

    public User findUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User UpdateUser(Long userId, User newUser)
    {
        Optional<User> user= Optional.ofNullable(userRepository.findById(userId).orElse(null));
        if (user.isPresent())
        {
            User foundUser=user.get();
            foundUser.setUsername(newUser.getUsername());
            foundUser.setPassword(newUser.getPassword());
            userRepository.save(foundUser);
            return foundUser;

        }
        else
        {
            return null;
        }
    }

    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }
}
