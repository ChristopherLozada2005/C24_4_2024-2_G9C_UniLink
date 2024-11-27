package com.example.demo.services.impl;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.User;
import com.example.demo.services.UserService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class UserServiceImplTest {

    @Autowired
    UserService userService;

    @Test
    void findAllUsers() {
        final int SIZE_EXPECTED = 12;

        List<User> users = userService.findAllUsers();
        final int SIZE_ACTUAL = users.size();

        assertEquals(SIZE_EXPECTED, SIZE_ACTUAL);
    }

    @Test
    void findUserById() {
        final String NAME_EXPECTED = "Mayo";

        final long USER_ID = 2;

        User user = userService.findUserById(USER_ID);
        final String NAME_ACTUAL = user.getName();

        assertEquals(NAME_EXPECTED, NAME_ACTUAL);
    }

    @Test
    void findUserByUsername() {
        final String USERNAME_EXPECTED = "mayo@gmail.com";

        final long USER_ID = 2;

        User user = userService.findUserById(USER_ID);
        final String USERNAME_ACTUAL = user.getUsername();

        assertEquals(USERNAME_EXPECTED, USERNAME_ACTUAL);
    }

    @Test
    void validateCredentials() {
        final String USERNAME = "Juanperez@gmail.com";
        final String PASSWORD = "password123";
        try {
            userService.validateCredentials(USERNAME, PASSWORD);
        } catch (UserNotFoundException e) {
            fail();
        }
    }

    @Test
    void createUser() {
        final String USERNAME = "user01@gmail.com";
        final String PASSWORD = "12345";
        User user = new User(USERNAME, PASSWORD);

        user = userService.createUser(user);
        User newUser = userService.findUserById(user.getId());

        if (newUser == null) {
            fail();
        }
        assertEquals(USERNAME, newUser.getUsername());
        assertEquals(PASSWORD, newUser.getPassword());
    }

    @Test
    void updateUser() {
        final String USERNAME = "user01@gmail.com";
        final String PASSWORD = "12345";
        User user = new User(USERNAME, PASSWORD);

        final String USERNAME_UPDATED = "updateduser01@gmail.com";
        final String PASSWORD_UPDATED = "12345updated";

        user = userService.createUser(user);

        user.setUsername(USERNAME_UPDATED);
        user.setPassword(PASSWORD_UPDATED);
        User updatedUser = userService.updateUser(user);

        assertEquals(USERNAME_UPDATED, updatedUser.getUsername());
        assertEquals(PASSWORD_UPDATED, updatedUser.getPassword());
    }

    @Test
    void deleteUserById() {
        final String USERNAME = "user01@gmail.com";
        final String PASSWORD = "12345";
        User user = new User(USERNAME, PASSWORD);

        User newUser = userService.createUser(user);

        try {
            userService.deleteUserById(newUser.getId());
        } catch (UserNotFoundException e) {
            fail(e.getMessage());
        }

        newUser = userService.findUserById(newUser.getId());
        if (newUser != null) {
            fail();
        }
    }
}