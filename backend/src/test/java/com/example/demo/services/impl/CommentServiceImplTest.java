package com.example.demo.services.impl;

import com.example.demo.exception.CommentNotFoundException;
import com.example.demo.model.Comment;
import com.example.demo.model.Post;
import com.example.demo.model.User;
import com.example.demo.services.CommentService;
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
class CommentServiceImplTest {

    @Autowired
    CommentService commentService;

    @Test
    void findAllComments() {
        final int SIZE_EXPECTED = 19;

        List<Comment> comments = commentService.findAllComments();
        final int SIZE_ACTUAL = comments.size();

        assertEquals(SIZE_EXPECTED, SIZE_ACTUAL);
    }

    @Test
    void findCommentsByPostId() {
        final int SIZE_EXPECTED = 3;
        long POST_ID = 1;

        List<Comment> comments = commentService.findCommentsByPostId(POST_ID);
        int SIZE_ACTUAL = comments.size();

        assertEquals(SIZE_EXPECTED, SIZE_ACTUAL);
    }

    @Test
    void findResponsesByCommentId() {
        final int SIZE_EXPECTED = 0;
        long COMM_ID = 1;

        List<Comment> comments = commentService.findResponsesByCommentId(COMM_ID);
        int SIZE_ACTUAL = comments.size();

        assertEquals(SIZE_EXPECTED, SIZE_ACTUAL);
    }

    @Test
    void findCommendById() {
        final String TEXT_EXPECTED = "¡Me encanta!";
        final int COMM_ID = 1;

        Comment comment = commentService.findCommendById(COMM_ID);

        String TEXT_ACTUAL = comment.getText();

        assertEquals(TEXT_EXPECTED, TEXT_ACTUAL);
    }

    @Test
    void createComment() {
        String TEXT = "new comment!";
        Long USER_ID = 3L;
        Long POST_ID = 3L;

        User user = new User();
        user.setId(USER_ID);
        Post post = new Post();
        post.setId(POST_ID);

        Comment comment = new Comment(null, TEXT, null, user, post, null);
        comment = commentService.createComment(comment);

        Comment newComment = commentService.findCommendById(comment.getId());
        assertEquals(TEXT, newComment.getText());
        assertEquals(USER_ID, newComment.getUser().getId());
        assertEquals(POST_ID, newComment.getPost().getId());
    }

    @Test
    void createResponse() {
        String TEXT = "new comment!";
        Long USER_ID = 3L;
        Long COMM_ID = 2L;

        User user = new User();
        user.setId(USER_ID);
        Comment comment = new Comment();
        comment.setId(COMM_ID);

        Comment response = new Comment(null, TEXT, null, user, null, comment);
        response = commentService.createResponse(response);

        Comment newResponse = commentService.findCommendById(response.getId());
        assertEquals(TEXT, newResponse.getText());
        assertEquals(USER_ID, newResponse.getUser().getId());
        assertEquals(COMM_ID, newResponse.getComment().getId());
    }

    @Test
    void updateComment() {
        String TEXT = "new comment!";
        Long USER_ID = 3L;
        Long POST_ID = 3L;

        User user = new User();
        user.setId(USER_ID);
        Post post = new Post();
        post.setId(POST_ID);

        String TEXT_UPDATED = "new comment!";
        Long USER_ID_UPDATED = 3L;
        Long POST_ID_UPDATED = 3L;

        User userUpdated = new User();
        userUpdated.setId(USER_ID_UPDATED);
        Post postUpdated = new Post();
        postUpdated.setId(POST_ID_UPDATED);

        Comment comment = new Comment(null, TEXT, null, user, post, null);
        comment = commentService.createComment(comment);

        comment.setText(TEXT_UPDATED);
        comment.setUser(userUpdated);
        comment.setPost(postUpdated);
        Comment updatedComment = commentService.updateComment(comment);

        assertEquals(TEXT_UPDATED, updatedComment.getText());
        assertEquals(USER_ID_UPDATED, updatedComment.getUser().getId());
        assertEquals(POST_ID_UPDATED, updatedComment.getPost().getId());
    }

    @Test
    void deleteCommentById() {
        String TEXT = "new comment!";
        Long USER_ID = 3L;
        Long POST_ID = 3L;

        User user = new User();
        user.setId(USER_ID);
        Post post = new Post();
        post.setId(POST_ID);

        Comment comment = new Comment(null, TEXT, null, user, post, null);
        comment = commentService.createComment(comment);

        try {
            commentService.deleteCommentById(comment.getId());
        } catch (CommentNotFoundException e) {
            fail(e.getMessage());
        }
    }
}


