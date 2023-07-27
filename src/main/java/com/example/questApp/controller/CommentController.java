package com.example.questApp.controller;

import com.example.questApp.entity.Comment;
import com.example.questApp.service.CommentServices;
import org.springframework.web.bind.annotation.*;
import requests.CommentCreateRequest;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController
{
    private CommentServices commentServices;

    public CommentController(CommentServices commentServices) {
        this.commentServices = commentServices;
    }

    @GetMapping
    public List<Comment> getComment(@RequestParam Optional<Long> postId, @RequestParam Optional<Long> userId)
    {
        return commentServices.getAllComments(postId,userId);
    }
    @GetMapping("/{commentId}")
    public Comment getCommentById(@PathVariable Long commentId)
    {
        return commentServices.getCommentById(commentId);
    }

    @PostMapping()
    public Comment setComment(@RequestBody  CommentCreateRequest newComment)
    {
        return commentServices.createNewComment(newComment);
    }
}
