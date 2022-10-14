package com.szaleski.restapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.szaleski.restapi.model.Post;
import com.szaleski.restapi.repository.PostRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    public Post getSinglePost(long id) {
        return postRepository.findById(id).orElseThrow();
    }
}
