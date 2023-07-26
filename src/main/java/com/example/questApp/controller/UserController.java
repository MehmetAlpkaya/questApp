package com.example.questApp.controller;

import com.example.questApp.entity.User;
import com.example.questApp.repository.UserRepository;
import com.example.questApp.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/deneme")
public class UserController {
    private UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUser()
    {
        return userService.getAllUsers();
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User newUser)
    {
           return userService.saveUser(newUser);
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable Long userId)
    {
       return userService.findUserById(userId);
    }


    @PutMapping("/{userId}")
    public User updateUser(@PathVariable Long userId, @RequestBody User newUser)
    {
        return userService.UpdateUser(userId,newUser);
    }
    @DeleteMapping("/{userId})")
    public void deleteUser(@PathVariable Long userId)

    {
        userService.deleteUserById(userId);
    }

}
