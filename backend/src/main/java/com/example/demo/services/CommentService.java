package com.example.demo.services;

import com.example.demo.exception.CommentNotFoundException;
import com.example.demo.model.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> findAllComments();
    List<Comment> findCommentsByPostId(long post_id);
    List<Comment> findResponsesByCommentId(long comment_id);
    Comment findCommendById(long comment_id);
    Comment createComment(Comment comment);
    Comment createResponse(Comment comment);
    Comment updateComment(Comment comment);
    void deleteCommentById(long id) throws CommentNotFoundException;

}
