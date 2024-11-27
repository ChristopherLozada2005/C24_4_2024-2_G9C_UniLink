package com.example.demo.web;

import com.example.demo.dto.PostDTO;
import com.example.demo.exception.PostNotFoundException;
import com.example.demo.model.Post;
import com.example.demo.model.User;
import com.example.demo.services.PostService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @GetMapping("/posts")
    public List<PostDTO> findAllPosts() {
        List<Post> posts = postService.findAllPosts();
        return posts.stream()
                .map(Post::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/posts/user/{user_id}")
    public List<PostDTO> findPostsByUserId(@PathVariable long user_id) {
        List<Post> posts = postService.findUserPosts(user_id);
        return posts.stream()
                .map(Post::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/posts/username/{user_name}")
    public List<PostDTO> findPostsByUsername(@PathVariable String user_name) {
        User user = userService.findUserByUsername(user_name);
        List<Post> posts = postService.findUserPosts(user.getId());
        return posts.stream()
                .map(Post::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/posts")
    public Post createPost(@RequestBody PostDTO postDTO) {
        User user = userService.findUserById(postDTO.getUserId());
        Post post = Post.fromDto(postDTO, user);
        return postService.createPost(post);
    }

    @GetMapping("/posts/{pub_id}")
    public Post findPostById(@PathVariable long pub_id) {
        return postService.findPostById(pub_id);
    }

    @PutMapping("/posts/{pub_id}")
    public Post updatePost(@PathVariable long pub_id, @RequestBody PostDTO postDTO) {
        Post post = postService.findPostById(pub_id);
        post.setTitle(postDTO.getTitle());
        post.setCategory(postDTO.getCategory());
        post.setDescription(postDTO.getDescription());
        return postService.updatePost(post);
    }

    @DeleteMapping("/posts/{pub_id}")
    public void deletePostById(@PathVariable long pub_id) {
        try {
            postService.deletePostById(pub_id);
        } catch (PostNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
