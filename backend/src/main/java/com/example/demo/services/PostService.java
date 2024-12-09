package com.example.demo.services;

import com.example.demo.exception.PostNotFoundException;
import com.example.demo.model.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {

    List<Post> findAllPosts();
    List<Post> findUserPosts(Long id);
    List<Post> findUserPostsByCategory(Long id, String category);
    List<Post> findPostsByCategory(String category);
    Post createPost(Post post);
    Post findPostById(Long id);
    Post updatePost(Post post);
    void deletePostById(Long id) throws PostNotFoundException;

}
