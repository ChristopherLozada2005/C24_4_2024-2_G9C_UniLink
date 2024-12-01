package com.example.demo.services;

import com.example.demo.exception.CommentNotFoundException;
import com.example.demo.model.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> findAllComments();
    List<Comment> findCommentsByPostId(long postId);
    List<Comment> findResponsesByReplyToId(long commentId);
    Comment findCommendById(long commentId);
    Comment createComment(Comment comment);
    Comment createResponse(Comment comment);
    Comment updateComment(Comment comment);
    void deleteCommentById(long id) throws CommentNotFoundException;

}
