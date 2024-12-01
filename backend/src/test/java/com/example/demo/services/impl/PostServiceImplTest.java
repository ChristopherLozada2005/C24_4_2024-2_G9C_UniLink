package com.example.demo.services.impl;

import com.example.demo.model.Post;
import com.example.demo.model.User;
import com.example.demo.services.PostService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Slf4j
@Transactional
class PostServiceImplTest {

    @Autowired
    PostService postService;

    @Test
    void findAllPosts() {
        final int SIZE_EXPECTED = 10;

        List<Post> posts = postService.findAllPosts();
        final int SIZE_ACTUAL = posts.size();

        assertEquals(SIZE_EXPECTED, SIZE_ACTUAL);
    }

    @Test
    void findUserPosts() {
        final String TITLE_EXPECTED = "Actualización de software";

        final long USER_ID = 2;
        List<Post> post = postService.findUserPosts(USER_ID);

        final String TITLE_ACTUAL = post.get(0).getTitle();

        assertEquals(TITLE_EXPECTED, TITLE_ACTUAL);
    }

    @Test
    void createPost() {
        final String TITLE = "New Post";
        final String DESCRIPTION = "Some description...";
        final String CATEGORY = "Some category";
        User user = new User();
        user.setId(1L);

        Post post = new Post(TITLE, DESCRIPTION, CATEGORY, null, user);

        Post newPub = postService.createPost(post);

        assertEquals(TITLE, newPub.getTitle());
        assertEquals(DESCRIPTION, newPub.getDescription());
        assertEquals(CATEGORY, newPub.getCategory());
        assertEquals(user.getId(), newPub.getUser().getId());
    }

    @Test
    void findPostById() {
        final String TITLE_EXPECTED = "Tutorial de Java";

        final long PUB_ID = 4;
        Post post = postService.findPostById(PUB_ID);

        final String TITLE_ACTUAL = post.getTitle();
        assertEquals(TITLE_EXPECTED, TITLE_ACTUAL);
    }

    @Test
    void updatePost() {
        final String TITLE = "New Post";
        final String DESCRIPTION = "Some description...";
        final User user = new User();
        user.setId(2L);

        final String TITLE_UPDATED = "Updated New Post";
        final String DESCRIPTION_UPDATED = "Updated description...";
        final User userUpdated = new User();
        userUpdated.setId(3L);

        Post newPost = new Post(TITLE,  DESCRIPTION, null, null, user);
        postService.createPost(newPost);

        newPost.setTitle(TITLE_UPDATED);
        newPost.setDescription(DESCRIPTION_UPDATED);
        newPost.setUser(userUpdated);

        Post updatedPost = postService.updatePost(newPost);

        assertEquals(TITLE_UPDATED, updatedPost.getTitle());
        assertEquals(DESCRIPTION_UPDATED, updatedPost.getDescription());
        assertEquals(userUpdated.getId(), updatedPost.getUser().getId());
    }

    void deletePostById() {
        /*
            w
         */
    }
}