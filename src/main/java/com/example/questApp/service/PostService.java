package com.example.questApp.service;

import com.example.questApp.entity.Like;
import com.example.questApp.entity.Post;
import com.example.questApp.entity.User;
import com.example.questApp.repository.PostRepository;
import com.example.questApp.requests.PostCreateRequest;
import com.example.questApp.response.LikeResponse;
import com.example.questApp.response.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import com.example.questApp.requests.PostUpdateRequest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    private PostRepository postRepository;
    private LikeService likeService;
    private UserService userService;

    public PostService(PostRepository postRepository,  UserService userService) {
        this.postRepository = postRepository;

        this.userService=userService;
    }

    @Lazy
    @Autowired
    public void setLikeService(LikeService likeService)
    {
        this.likeService=likeService;
    }


    public List<PostResponse> getAllPost(Optional<Long> userId)
    {
        List<Post> list;
        if (userId.isPresent())
        {
            list= postRepository.findByUserId(userId.get());
        }
        else {
            list= postRepository.findAll();}

        return  list.stream().map(p -> { // getting likes for per post and when creating postResponse it setting likes into the response
            List<LikeResponse> likes =likeService.getAllLike(null,Optional.of(p.getId())); // To show the number of likes in the client.
            return  new PostResponse(p, likes);}).collect(Collectors.toList());
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
