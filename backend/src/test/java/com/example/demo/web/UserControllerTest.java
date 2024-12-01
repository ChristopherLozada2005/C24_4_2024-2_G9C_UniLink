package com.example.demo.web;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void findAllUsers() throws Exception {
        final int SIZE_EXPECTED = 12;

        this.mockMvc.perform(get("/users"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$", hasSize(SIZE_EXPECTED)));
    }

    @Test
    void findUserById() throws Exception {
        final String EXPECTED_USERNAME = "mayo@gmail.com";
        final int USER_ID = 2;

        this.mockMvc.perform(get("/users/" + USER_ID))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.username", is(EXPECTED_USERNAME)));
    }

    @Test
    void findUserByUsername() throws Exception {
        final String EXPECTED_NAME = "Juan";
        final String USERNAME = "juanperez@gmail.com";

        this.mockMvc.perform(get("/users/username/" + USERNAME))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.name", is(EXPECTED_NAME)));
    }
}