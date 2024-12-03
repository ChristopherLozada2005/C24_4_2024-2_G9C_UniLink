package com.example.demo.services.impl;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    JWTService jwtService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void validateCredentials(String username, String password) throws UserNotFoundException {
        User user = userRepository.findByUsernameAndPassword(username, password);
        if (user != null) {
            return;
        }
        throw new UserNotFoundException("Invalid Credentials");
    }

    @Override
    public User createUser(User user) {
        User newUser = new User();
        newUser.setName(user.getName());
        newUser.setLastname(user.getLastname());
        newUser.setAge(user.getAge());
        newUser.setGender(user.getGender());
        newUser.setPassword(user.getPassword());
        newUser.setUsername(user.getUsername());
        return userRepository.save(newUser);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(long id) {
        userRepository.deleteById(id);
    }


    public String verifyUser(User user, HttpServletResponse response) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                user.getUsername(),
                user.getPassword()
        ));
        User userFromModel = userRepository.findByUsername(user.getUsername());
        if(authentication.isAuthenticated()){
            return jwtService.getToken(user.getUsername(), userFromModel.getId(), userFromModel.getName(), userFromModel.getHasImage());
        }
        return "ERROR";
    }

}
