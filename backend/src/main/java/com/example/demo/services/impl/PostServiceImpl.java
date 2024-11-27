package com.example.demo.services.impl;

import com.example.demo.model.Post;
import com.example.demo.repository.PostRepository;
import com.example.demo.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;

    @Override
    public List<Post> findAllPosts() {
        return postRepository.findAllByOrderByPubDateDesc();
    }

    @Override
    public List<Post> findUserPosts(Long user_id) {
        return postRepository.findByUserIdOrderByPubDateDesc(user_id);
    }

    @Override
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post findPostById(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    @Override
    public Post updatePost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public void deletePostById(Long id) {
        try {
            postRepository.deleteById(id);
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}