package com.example.demo.web;

import com.example.demo.dto.PostDTO;
import com.example.demo.model.Post;
import com.example.demo.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
@AutoConfigureMockMvc
class PostControllerTest {


    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @Test
    void findAllPosts() throws Exception {
        final int SIZE_EXPECTED = 10;
        this.mockMvc.perform(get("/posts"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$", hasSize(SIZE_EXPECTED)));
    }

    @Test
    void findPostsByUserId() throws Exception {
        String EXPECTED_TITLE = "Conferencia sobre IA";
        final int POST_ID = 3;

        this.mockMvc.perform(get("/posts/" + POST_ID))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.title", is(EXPECTED_TITLE)));
    }

    @Test
    void findPostsByUsername() throws Exception {
        final int EXPECTED_SIZE = 1;
        final String USER_USERNAME = "juanperez@gmail.com";

        this.mockMvc.perform(get("/posts/username/" + USER_USERNAME))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$", hasSize(EXPECTED_SIZE)));
    }

    @Test
    void createPost() throws Exception {
        String TITLE = "title";
        String DESCRIPTION = "description";
        Long USER_ID = 3L;

        PostDTO newPost = new PostDTO();
        newPost.setTitle(TITLE);
        newPost.setDescription(DESCRIPTION);
        newPost.setUserId(USER_ID);

        mockMvc.perform(post("/posts")
                    .content(objectMapper.writeValueAsString(newPost))
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.title", is(TITLE)));
    }

    @Test
    void findPostById() {
    }

    @Test
    void updatePost() {
    }

    @Test
    void deletePostById() {
    }
}