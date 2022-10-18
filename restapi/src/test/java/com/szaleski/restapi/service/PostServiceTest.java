package com.szaleski.restapi.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.szaleski.restapi.model.Post;

@SpringBootTest
class PostServiceTest {

    @Autowired
    private PostService postService;

    @Test
    public void shouldGetPost() {
        // Given
        // When
        Post singlePost = postService.getSinglePost(1L);

        // Then
        assertThat(singlePost).isNotNull();
        assertThat(singlePost.getId()).isEqualTo(1);
    }

}