package com.example.demo.services;

import com.example.demo.model.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> findAllComments();
    List<Comment> findPostComments(long id);
    List<Comment> findResponses(long id);
    Comment findCommendById(long id);
    Comment postComment(Comment comment);
    Comment createResponse(Comment comment);
    Comment updateComment(Comment comment);
    void deleteComment(long id);

}
