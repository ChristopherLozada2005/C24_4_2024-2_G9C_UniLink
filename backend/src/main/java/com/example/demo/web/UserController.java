package com.example.demo.web;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.User;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> findAllUsers() {
        return userService.findAllUsers();
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userService.createUser(user);
    }

    @DeleteMapping("/users/{userId}")
    public void deleteUser(@PathVariable long userId) {
        try {
            userService.deleteUserById(userId);
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }
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
