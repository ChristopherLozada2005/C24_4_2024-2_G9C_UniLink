package com.example.demo.web;

import com.example.demo.dto.CommentDTO;
import com.example.demo.exception.CommentNotFoundException;
import com.example.demo.model.Comment;
import com.example.demo.model.Post;
import com.example.demo.model.User;
import com.example.demo.services.CommentService;
import com.example.demo.services.PostService;
import com.example.demo.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class CommentController {

    private final CommentService commentService;
    private final UserService userService;
    private final PostService postService;

    @Autowired
    public CommentController(CommentService commentService, UserService userService, PostService postService) {
        this.commentService = commentService;
        this.userService = userService;
        this.postService = postService;
    }

    @GetMapping("/comments")
    public List<Comment> findAllComments() {
        return  commentService.findAllComments();
    }

    @GetMapping("/comments/post/{postId}")
    public List<CommentDTO> findCommentsByPostId(@PathVariable long postId) {
        List<Comment> comments = commentService.findCommentsByPostId(postId);
        return comments.stream()
                .map(Comment::toDto)
                .toList();
    }

    @GetMapping("/comments/{commId}")
    public CommentDTO findCommentById(@PathVariable long commId) {
        Comment comment = commentService.findCommendById(commId);
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
        Comment comment = commentService.findCommendById(commentDTO.getReplyToId());
        Comment response = new Comment();
        response.setText(commentDTO.getText());
        response.setReplyTo(comment);
        return commentService.createResponse(response);
    }

    @PutMapping("/comments")
    public Comment updateComment(@RequestBody CommentDTO commentDTO) {
        Comment comment = commentService.findCommendById(commentDTO.getId());
        comment.setText(commentDTO.getText());
        return commentService.updateComment(comment);
    }

    @DeleteMapping("/comments/{commId}")
    public void deleteCommentById(@PathVariable long commId) {
        try {
            commentService.deleteCommentById(commId);
        } catch (CommentNotFoundException e) {
            log.info(e.getMessage());
        }
    }

    @GetMapping("/comments/responses/{commId}")
    public List<Comment> findResponsesByReplyToId(@PathVariable long commId) {
        return commentService.findResponsesByReplyToId(commId);
    }

}