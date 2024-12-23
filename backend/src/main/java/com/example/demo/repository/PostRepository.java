package com.example.demo.repository;

import com.example.demo.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByUserIdOrderByPubDateDesc(long userId);
    List<Post> findAllByOrderByPubDateDesc();

    List<Post> findByUserIdAndCategoryOrderByPubDateDesc(long userId, String category);
    List<Post> findByCategoryOrderByPubDateDesc(String category);

}
