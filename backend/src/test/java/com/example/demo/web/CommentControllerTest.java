package com.example.demo.web;

import com.example.demo.dto.CommentDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Transactional
class CommentControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @Test
    void findAllComments() throws Exception {

        final int SIZE_EXPECTED = 19;

        this.mockMvc.perform(get("/comments"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$", hasSize(SIZE_EXPECTED)));
    }

    @Test
    void findCommentsByPostId() throws Exception {
        final int SIZE_EXPECTED = 2;
        final int POST_ID = 2;

        this.mockMvc.perform(get("/comments/post/" + POST_ID))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$", hasSize(SIZE_EXPECTED)));
    }

    @Test
    void createComment() throws Exception {
        String TEXT = "new comment!";
        Long USER_ID = 3L;
        Long POST_ID = 3L;

        CommentDTO newComment= new CommentDTO();
        newComment.setText(TEXT);
        newComment.setUserId(USER_ID);
        newComment.setPostId(POST_ID);

        mockMvc.perform(post("/comments")
                    .content(objectMapper.writeValueAsString(newComment))
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.text", is(TEXT)));
    }

    @Test
    void createResponse() throws Exception {
        String TEXT = "new comment!";
        Long USER_ID = 3L;
        Long COMM_ID = 3L;

        CommentDTO newResponse= new CommentDTO();
        newResponse.setText(TEXT);
        newResponse.setUserId(USER_ID);
        newResponse.setCommendId(COMM_ID);

        mockMvc.perform(post("/comments/responses")
                .content(objectMapper.writeValueAsString(newResponse))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.text", is(TEXT)));
    }

    @Test
    void updateComment() throws Exception {
        String TEXT = "new comment!";
        Long USER_ID = 3L;
        Long POST_ID = 3L;

        String TEXT_UPDATED = "Updated new comment!";
        Long USER_ID_UPDATED = 3L;
        Long POST_ID_UPDATED = 3L;

        CommentDTO newComment = new CommentDTO();
        newComment.setText(TEXT);
        newComment.setUserId(USER_ID);
        newComment.setPostId(POST_ID);

        ResultActions mvcActions = mockMvc.perform(post("/comments")
                .content(objectMapper.writeValueAsString(newComment))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print());

        String response = mvcActions.andReturn().getResponse().getContentAsString();
        Integer id = JsonPath.parse(response).read("$.id");

        CommentDTO updatedComment = new CommentDTO();
        updatedComment.setId(Long.valueOf(id));
        updatedComment.setText(TEXT_UPDATED);
        updatedComment.setUserId(USER_ID_UPDATED);
        updatedComment.setPostId(POST_ID_UPDATED);

        mockMvc.perform(put("/comments")
                        .content(objectMapper.writeValueAsString(updatedComment))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print());

        mockMvc.perform(get("/comments/" + id))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.text", is(TEXT_UPDATED)))
                .andDo(print());
    }

    @Test
    void deleteCommentById() throws Exception {
        String TEXT = "new comment!";
        Long USER_ID = 3L;
        Long COMM_ID = 3L;

        CommentDTO newResponse= new CommentDTO();
        newResponse.setText(TEXT);
        newResponse.setUserId(USER_ID);
        newResponse.setCommendId(COMM_ID);

        ResultActions mvcActions = mockMvc.perform(post("/comments/responses")
                        .content(objectMapper.writeValueAsString(newResponse))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.text", is(TEXT)));

        String response = mvcActions.andReturn().getResponse().getContentAsString();
        Integer id = JsonPath.parse(response).read("$.id");

        mockMvc.perform(delete("/comments/" + id))
                .andExpect(status().isOk());
    }

    @Test
    void getResponsesByCommentId() throws Exception {
        final int SIZE_EXPECTED = 0;
        final int COMM_ID = 2;

        this.mockMvc.perform(get("/comments/responses/" + COMM_ID))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$", hasSize(SIZE_EXPECTED)));
    }
}