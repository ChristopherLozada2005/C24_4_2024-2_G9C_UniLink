package com.example.demo.web;

import com.example.demo.model.User;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public List<User> findAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/users/{user_id}")
    public User findUserById(@PathVariable long user_id) {
        return userService.findUserById(user_id);
    }

    @GetMapping("/users/username/{user_name}")
    public User findUserByUsername(@PathVariable String user_name) {
        return userService.findUserByUsername(user_name);
    }



}
