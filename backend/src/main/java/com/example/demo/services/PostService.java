package com.example.demo.services;

import com.example.demo.model.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {

    List<Post> findAllPosts();
    List<Post> findUserPosts(Long id);
    Post createPost(Post post);
    Post findPostById(Long id);
    Post updatePost(Post post);
    void deletePostById(Long id);

}
