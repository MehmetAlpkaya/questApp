package com.example.questApp.controller;

import com.example.questApp.entity.Post;
import requests.PostCreateRequest;
import com.example.questApp.service.PostService;
import org.springframework.web.bind.annotation.*;
import requests.PostUpdateRequest;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController
{
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<Post> getAllPost(@RequestParam Optional<Long> userId)
    {
        return postService.getAllPost(userId);
    }

    @GetMapping("/{postId}")
    public Post getPostById(@PathVariable Long postId)
    {
        return postService.getPostById(postId);
    }

    @PostMapping
    public Post createPost(@RequestBody PostCreateRequest newPost)
    {
        return postService.createOnePost(newPost);
    }

    @PutMapping("/{postId}")
    public Post updateOnePost(@PathVariable Long postId, @RequestBody PostUpdateRequest updatePost) {
        return postService.updateOnePostById(postId, updatePost);
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable Long postId)
    {
        postService.deletePost(postId);
    }
}
