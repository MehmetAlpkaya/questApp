package com.example.questApp.controller;

import com.example.questApp.entity.Post;
import com.example.questApp.response.PostCreateRequest;
import com.example.questApp.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("posts")
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

    @PostMapping()
    public Post createPost(@RequestBody PostCreateRequest newPost)
    {
        return postService.createOnePost(newPost);
    }



}
