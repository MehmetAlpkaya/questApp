package com.example.questApp.service;

import com.example.questApp.entity.Comment;
import com.example.questApp.entity.Post;
import com.example.questApp.entity.User;
import com.example.questApp.repository.CommentRepository;
import org.springframework.stereotype.Service;
import requests.CommentCreateRequest;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServices
{
    CommentRepository commentRepository;
    UserService userService;
    PostService postService;

    public CommentServices(CommentRepository commentRepository, UserService userService, PostService postService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.postService = postService;
    }



    public List<Comment> getAllComments(Optional<Long> postId, Optional<Long> userId)
    {
        if(userId.isPresent() && postId.isPresent())
        {
            return commentRepository.findByUserIdAndPostId(userId.get(),postId.get());
        } else if (userId.isPresent())
        {
            return commentRepository.findByUserId(userId.get());
            
        } else if (postId.isPresent()) {
            return commentRepository.findByPostId(postId.get());

        }
        else
            return commentRepository.findAll();

    }

    public Comment getCommentById(Long commentId)
    {
        return commentRepository.findById(commentId).orElse(null);
    }

    public Comment createNewComment(CommentCreateRequest newComment)
    {
        User user= userService.getUserById(newComment.getUserId());
        Post post=postService.getPostById(newComment.getPostId());
        if(user!=null || post!=null)
        {
        Comment coment=new Comment();
        coment.setId(newComment.getId());
        coment.setText(newComment.getText());
        coment.setUser(user);
        coment.setPost(post);
        return commentRepository.save(coment);
        }
        else
            return null;




    }
}
