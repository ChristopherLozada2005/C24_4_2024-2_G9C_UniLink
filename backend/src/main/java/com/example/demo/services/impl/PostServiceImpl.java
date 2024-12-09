package com.example.demo.services.impl;

import com.example.demo.model.Post;
import com.example.demo.repository.PostRepository;
import com.example.demo.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> findAllPosts() {
        return postRepository.findAllByOrderByPubDateDesc();
    }

    @Override
    public List<Post> findUserPosts(Long userId) {
        return postRepository.findByUserIdOrderByPubDateDesc(userId);
    }

    @Override
    public List<Post> findUserPostsByCategory(Long id, String category) {
        return postRepository.findByUserIdAndCategoryOrderByPubDateDesc(id, category);
    }

    @Override
    public List<Post> findPostsByCategory(String category) {
        return postRepository.findByCategoryOrderByPubDateDesc(category);
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
        postRepository.deleteById(id);
    }
}
