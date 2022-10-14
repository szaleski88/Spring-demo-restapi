package com.szaleski.restapi.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.szaleski.restapi.model.Comment;
import com.szaleski.restapi.model.Post;
import com.szaleski.restapi.repository.CommentRepository;
import com.szaleski.restapi.repository.PostRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

    private static final int PAGE_SIZE = 20;

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public List<Post> getPosts(int page, Sort.Direction sort) {
        return postRepository.findAllPosts(PageRequest.of(page, PAGE_SIZE, Sort.by(sort, "id")));
    }

    public Post getSinglePost(long id) {
        return postRepository.findById(id).orElseThrow();
    }

    public List<Post> getPostsWithComments(int page, Sort.Direction sort) {
        List<Post> allPosts = postRepository.findAllPosts(PageRequest.of(page, PAGE_SIZE, Sort.by(sort, "id")));
        List<Long> ids = allPosts.stream().map(Post::getId).collect(Collectors.toList());

        List<Comment> comments = commentRepository.findAllByPostIdIn(ids);

        allPosts.forEach(post -> post.setComments(extractComments(comments, post.getId())));
        return allPosts;
    }

    private List<Comment> extractComments(List<Comment> comments, long id) {
        return comments.stream()
                       .filter(comment -> comment.getPostId() == id)
                       .collect(Collectors.toList());
    }

    public Post addPost(Post post) {
        return postRepository.save(post);
    }

    @Transactional
    public Post editPost(Post post) {
        Post postEdited = postRepository.findById(post.getId()).orElseThrow();

        postEdited.setTitle(postEdited.getTitle());
        postEdited.setContent(postEdited.getContent());

        return postEdited; // hibernate dirty checking - updates modified entities in db automatically. no need to save!
    }
}
