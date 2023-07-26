package com.example.questApp.service;

import com.example.questApp.entity.Post;
import com.example.questApp.entity.User;
import com.example.questApp.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    public List<Post> getAllPost(Optional<Long> userId)
    {
        if (userId.isPresent())
        {
            return postRepository.findByUserId(userId.get());
        }
        return postRepository.findAll();
    }
}
