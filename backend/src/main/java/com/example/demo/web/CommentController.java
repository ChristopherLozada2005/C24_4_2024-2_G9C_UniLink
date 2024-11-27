package com.example.demo.web;

import com.example.demo.dto.CommentDTO;
import com.example.demo.exception.CommentNotFoundException;
import com.example.demo.model.Comment;
import com.example.demo.model.Post;
import com.example.demo.model.User;
import com.example.demo.services.CommentService;
import com.example.demo.services.PostService;
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
    PostService postService;

    @GetMapping("/comments")
    public List<Comment> findAllComments() {
        return  commentService.findAllComments();
    }

    @GetMapping("/comments/post/{post_id}")
    public List<CommentDTO> findCommentsByPostId(@PathVariable long post_id) {
        List<Comment> comments = commentService.findCommentsByPostId(post_id);
        return comments.stream()
                .map(Comment::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/comments/{comm_id}")
    public CommentDTO findCommentById(@PathVariable long comm_id) {
        Comment comment = commentService.findCommendById(comm_id);
        return Comment.toDto(comment);
    }

    @PostMapping("/comments")
    public Comment createComment(@RequestBody CommentDTO commentDTO) {
        Post post = postService.findPostById(commentDTO.getPostId());
        User user = userService.findUserById(commentDTO.getUserId());
        Comment comment = new Comment();
        comment.setText(commentDTO.getText());
        comment.setPost(post);
        comment.setUser(user);
        return commentService.createComment(comment);
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
        Comment comment = commentService.findCommendById(commentDTO.getId());
        comment.setText(commentDTO.getText());
        return commentService.updateComment(comment);
    }

    @DeleteMapping("/comments/{comm_id}")
    public void deleteCommentById(@PathVariable long comm_id) {
        try {
            commentService.deleteCommentById(comm_id);
        } catch (CommentNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/comments/responses/{comm_id}")
    public List<Comment> getResponsesByCommentId(@PathVariable long comm_id) {
        return commentService.findResponsesByCommentId(comm_id);
    }

}