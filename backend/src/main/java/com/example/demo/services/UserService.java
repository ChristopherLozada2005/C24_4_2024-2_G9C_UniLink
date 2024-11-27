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
    void deleteUser(long id);

    String verify(User user, HttpServletResponse response);

}
