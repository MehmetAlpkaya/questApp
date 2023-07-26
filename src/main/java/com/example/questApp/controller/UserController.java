package com.example.questApp.controller;

import com.example.questApp.entity.User;
import com.example.questApp.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/deneme")
public class UserController {
    private UserRepository userRepository;
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> getAllUser()
    {
        return userRepository.findAll();
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User newUser)
    {
           return userRepository.save(newUser);
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable Long userId)
    {
       return userRepository.findById(userId).orElse(null);
    }


    @PutMapping("/{userId}")
    public User updateUser(@PathVariable Long userId, @RequestBody User newUser)
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
    @DeleteMapping("/{userId})")
    public void deleteUser(@PathVariable Long userId)
    {
        userRepository.deleteById(userId);
    }

}
