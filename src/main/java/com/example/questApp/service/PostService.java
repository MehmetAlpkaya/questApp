package com.example.questApp.service;

import com.example.questApp.entity.Post;
import com.example.questApp.entity.User;
import com.example.questApp.repository.PostRepository;
import com.example.questApp.requests.PostCreateRequest;
import org.springframework.stereotype.Service;
import com.example.questApp.requests.PostUpdateRequest;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private PostRepository postRepository;
    private UserService userService;

    public PostService(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService=userService;
    }


    public List<Post> getAllPost(Optional<Long> userId)
    {
        if (userId.isPresent())
        {
            return postRepository.findByUserId(userId.get());
        }
        return postRepository.findAll();
    }

    public Post getPostById(Long postId)
    {

        return postRepository.findById(postId).orElse(null);
    }


    public Post createOnePost(PostCreateRequest newPostRequest)
    {
        User user=userService.getUserById(newPostRequest.getUserId());
        if(user==null)
            return null;
        Post toSave=new Post();
        toSave.setId(newPostRequest.getId());
        toSave.setText(newPostRequest.getText());
        toSave.setTitle(newPostRequest.getTitle());
        toSave.setUser(user);
        return postRepository.save(toSave);
    }
    public void deletePost(Long postId)
    {
        postRepository.deleteById(postId);
    }
    public Post updateOnePostById(Long postId, PostUpdateRequest updatePost) {

            Optional<Post> post = postRepository.findById(postId);
            if(post.isPresent()) {
                Post toUpdate = post.get();
                toUpdate.setText(updatePost.getText());
                toUpdate.setTitle(updatePost.getTitle());
                postRepository.save(toUpdate);
                return toUpdate;
            }
            return null;

    }
}
