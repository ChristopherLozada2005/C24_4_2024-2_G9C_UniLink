package com.example.demo.web;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.User;
import com.example.demo.services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Autowired
    public UserService userService;

    @PostMapping("/login")
    public String login(@RequestBody UserDTO userDTO, HttpServletResponse response) {
        User user = new User(userDTO.getUsername(), userDTO.getPassword());
        return userService.verifyUser(user, response);
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userService.createUser(user);
    }
}
