package com.example.demo.web;

import com.example.demo.model.User;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> findAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/users/{userId}")
    public User findUserById(@PathVariable long userId) {
        return userService.findUserById(userId);
    }

    @GetMapping("/users/username/{userName}")
    public User findUserByUsername(@PathVariable String userName) {
        return userService.findUserByUsername(userName);
    }

}
