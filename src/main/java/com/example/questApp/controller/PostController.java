package com.example.questApp.controller;

import com.example.questApp.entity.Post;
import com.example.questApp.service.PostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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



}
