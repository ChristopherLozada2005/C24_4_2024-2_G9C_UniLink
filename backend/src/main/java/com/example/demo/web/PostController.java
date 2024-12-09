package com.example.demo.web;

import com.example.demo.dto.PostDTO;
import com.example.demo.exception.PostNotFoundException;
import com.example.demo.model.Post;
import com.example.demo.model.User;
import com.example.demo.repository.CommentRepository;
import com.example.demo.services.PostService;
import com.example.demo.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class PostController {

    private final PostService postService;
    private final UserService userService;
    private final CommentRepository commentRepository;

    public PostController(PostService postService, UserService userService, CommentRepository commentRepository) {
        this.postService = postService;
        this.userService = userService;
        this.commentRepository = commentRepository;
    }

    @GetMapping("/posts")
    public List<PostDTO> findAllPosts() {
        List<Post> posts = postService.findAllPosts();
        return posts.stream()
                .map(Post::toDto)
                .toList();
    }

    @GetMapping("/posts/user/{userId}")
    public List<PostDTO> findPostsByUserId(@PathVariable long userId) {
        List<Post> posts = postService.findUserPosts(userId);
        return posts.stream()
                .map(post -> {
                    int commentCount = commentRepository.countByPostId(post.getId());
                    return new PostDTO(post.getId(), post.getTitle(), post.getDescription(), post.getCategory(), post.getHasImage(),
                            post.getUser().getId(), post.getPubDate(), post.getUser().getName(), commentCount, null, post.getUser());
                })
                .toList();
    }

    @GetMapping("/posts/category/{userId}/{category}")
    public List<PostDTO> findPostsByUserIdAndCategory(@PathVariable Long userId, @PathVariable String category) {
        List<Post> posts = postService.findUserPostsByCategory(userId, category);
        return posts.stream()
                .map(post -> {
                    int commentCount = commentRepository.countByPostId(post.getId());
                    return new PostDTO(post.getId(), post.getTitle(), post.getDescription(), post.getCategory(), post.getHasImage(),
                            post.getUser().getId(), post.getPubDate(), post.getUser().getName(), commentCount, null, post.getUser()) ;
                })
                .toList();
    }

    @GetMapping("/posts/category/{postCategory}")
    public List<PostDTO> findPostsByCategory(@PathVariable String postCategory) {
        List<Post> posts = postService.findPostsByCategory(postCategory);
        return posts.stream()
                .map(post -> {
                    int commentCount = commentRepository.countByPostId(post.getId());
                    return new PostDTO(post.getId(), post.getTitle(), post.getDescription(), post.getCategory(), post.getHasImage(),
                            post.getUser().getId(), post.getPubDate(), post.getUser().getName(), commentCount, null, post.getUser());
                })
                .toList();
    }

    @GetMapping("/posts/username/{userName}")
    public List<PostDTO> findPostsByUsername(@PathVariable String userName) {
        User user = userService.findUserByUsername(userName);
        List<Post> posts = postService.findUserPosts(user.getId());
        return posts.stream()
                .map(Post::toDto)
                .toList();
    }

    @PostMapping("/posts")
    public Post createPost(@RequestBody PostDTO postDTO) {
        User user = userService.findUserById(postDTO.getUserId());
        Post post = Post.fromDto(postDTO, user);
        return postService.createPost(post);
    }

    @GetMapping("/posts/{pubId}")
    public Post findPostById(@PathVariable long pubId) {

        return postService.findPostById(pubId);
    }

    @PutMapping("/posts/{pubId}")
    public Post updatePost(@PathVariable long pubId, @RequestBody PostDTO postDTO) {
        Post post = postService.findPostById(pubId);
        post.setTitle(postDTO.getTitle());
        post.setCategory(postDTO.getCategory());
        post.setDescription(postDTO.getDescription());
        return postService.updatePost(post);
    }

    @DeleteMapping("/posts/{pubId}")
    public void deletePostById(@PathVariable long pubId) {
        try {
            postService.deletePostById(pubId);
        } catch (PostNotFoundException e) {
            log.info(e.getMessage());
        }
    }

}
