package com.example.demo.services;

import com.example.demo.model.User;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public interface UserService {

    User validateCredentials(String username, String password);

    List<User> findAllUsers();
    User findUserById(long id);
    User findUserByUsername(String username);
    User createUser(User user);
    User updateUser(User user);
    void deleteUserById(long id);

    String verifyUser(User user, HttpServletResponse response);

}
