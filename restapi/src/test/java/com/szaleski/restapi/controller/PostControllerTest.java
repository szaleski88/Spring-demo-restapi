package com.szaleski.restapi.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.szaleski.restapi.model.Post;
import com.szaleski.restapi.repository.PostRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PostRepository postRepository;

    @Test
    public void getSinglePost_failsWith401_whenNotAuthorized() throws Exception {
        // Given

        // When
        mockMvc.perform(get("/posts/1"))
               .andDo(print())
               .andExpect(status().is(401));
    }

    @WithMockUser
    @Test
    @Transactional
    public void shouldGetSinglePost() throws Exception {
        // Given
        Post post = new Post();
        post.setTitle("Test");
        post.setContent("Test content");
        post.setCreated(LocalDateTime.now());

        Post savedPost = postRepository.save(post);

        // When
        mockMvc.perform(get("/posts/" + post.getId()))
               .andDo(print())
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id").value(Matchers.is((int)savedPost.getId())));
        // Then
    }

}