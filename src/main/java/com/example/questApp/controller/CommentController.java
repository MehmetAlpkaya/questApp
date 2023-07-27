package com.example.questApp.controller;

import com.example.questApp.entity.Comment;
import com.example.questApp.service.CommentServices;
import org.springframework.web.bind.annotation.*;
import com.example.questApp.requests.CommentCreateRequest;
import com.example.questApp.requests.CommentUpdateRequest;

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
    @PutMapping("/{commentId}")
    public Comment updateComment(@PathVariable Long commentId, @RequestBody CommentUpdateRequest commentUpdateRequest)
    {
        return commentServices.updateComment(commentId, commentUpdateRequest);
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Long commentId)
    {
        commentServices.deleteComment(commentId);
    }
}
