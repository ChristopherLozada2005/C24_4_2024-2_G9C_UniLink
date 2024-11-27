package com.example.demo.services.impl;

import com.example.demo.model.Comment;
import com.example.demo.repository.CommentRepository;
import com.example.demo.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Override
    public List<Comment> findAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public List<Comment> findPostComments(long id) {
        return commentRepository.findByPublicationId(id);
    }

    @Override
    public List<Comment> findResponses(long id) {
        return commentRepository.findByCommentId(id);
    }

    @Override
    public Comment findCommendById(long id) {
        return commentRepository.findById(id).orElse(null);
    }

    @Override
    public Comment postComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment createResponse(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment updateComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public void deleteComment(long id) {
        commentRepository.deleteById(id);
    }
}
