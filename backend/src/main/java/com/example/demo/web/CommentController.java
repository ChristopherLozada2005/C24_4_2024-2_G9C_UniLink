package com.example.demo.web;

import com.example.demo.dto.CommentDTO;
import com.example.demo.model.Comment;
import com.example.demo.model.Publication;
import com.example.demo.model.User;
import com.example.demo.services.CommentService;
import com.example.demo.services.PublicationService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    UserService userService;

    @Autowired
    PublicationService publicationService;

    @GetMapping("/comments")
    public List<Comment> findAllComments() {
        return  commentService.findAllComments();
    }

    @GetMapping("/comments/post/{post_id}")
    public List<CommentDTO> getPostComments(@PathVariable long post_id) {
        List<Comment> comments = commentService.findPostComments(post_id);
        return comments.stream()
                .map(Comment::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/comments")
    public Comment createComment(@RequestBody CommentDTO commentDTO) {
        Publication publication = publicationService.findPublicationById(commentDTO.getPublicationId());
        User user = userService.findUserById(commentDTO.getUserId());
        Comment comment = new Comment();
        comment.setText(commentDTO.getText());
        comment.setPublication(publication);
        comment.setUser(user);
        return commentService.postComment(comment);
    }

    @PostMapping("/comments/responses")
    public Comment createResponse(@RequestBody CommentDTO commentDTO) {
        Comment comment = commentService.findCommendById(commentDTO.getCommendId());
        Comment response = new Comment();
        response.setText(commentDTO.getText());
        response.setComment(comment);
        return commentService.createResponse(response);
    }

    @PutMapping("/comments")
    public Comment updateComment(@RequestBody CommentDTO commentDTO) {
        Comment comment = commentService.findCommendById(commentDTO.getPublicationId());
        comment.setText(commentDTO.getText());
        return commentService.updateComment(comment);
    }

    @DeleteMapping("/comments/{comm_id}")
    public void deleteComment(@PathVariable long comm_id) {
        commentService.deleteComment(comm_id);
    }

    @GetMapping("/comments/responses/{comm_id}")
    public List<Comment> getResponses(@PathVariable long comm_id) {
        return commentService.findResponses(comm_id);
    }

}