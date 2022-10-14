package com.szaleski.restapi.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.szaleski.restapi.model.Post;

public class PostDtoMapper {

    private PostDtoMapper() {
    }

    public static List<PostDto> mapToPostDtos(List<Post> posts) {
        return posts.stream().map(PostDtoMapper::mapToPostDto).collect(Collectors.toList());
    }

    public static PostDto mapToPostDto(Post post) {
        return PostDto.builder()
                      .id(post.getId())
                      .title(post.getTitle())
                      .content(post.getContent())
                      .created(post.getCreated())
                      .build();
    }
}
