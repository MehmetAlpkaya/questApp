package com.example.questApp.service;

import com.example.questApp.entity.Like;
import com.example.questApp.entity.Post;
import com.example.questApp.entity.User;
import com.example.questApp.repository.LikeRepository;
import com.example.questApp.response.LikeResponse;
import org.springframework.stereotype.Service;
import com.example.questApp.requests.LikeCreateRequest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LikeService {

    private LikeRepository likeRepository;
    private UserService userService;
    private PostService postService;

    public LikeService(LikeRepository likeRepository, UserService userService,
                       PostService postService) {
        this.likeRepository = likeRepository;
        this.userService = userService;
        this.postService = postService;
    }




    public List<LikeResponse> getAllLike(Optional<Long> userId, Optional<Long> postId)
    {
        List<Like> list;
        if(userId.isPresent() && postId.isPresent())
        {
            list= likeRepository.findAllByUserIdAndPostId(userId,postId);
        } else if (userId.isPresent())
        {
            list= likeRepository.findByUserId(userId.get());

        } else if (postId.isPresent())
        {
         list= likeRepository.findByPostId(postId);
        }
        else
            list= likeRepository.findAll();
        return list.stream().map(like-> new LikeResponse(like)).collect(Collectors.toList());
    }


    public Like findLikeById(Long likeId)
    {

            return likeRepository.findById(likeId).orElse(null);

    }

    public Like createLike(LikeCreateRequest newLike)
    {
        User user=userService.getUserById(newLike.getUserId());
        Post post=postService.getPostById(newLike.getPostId());
        if(user!=null && post!=null)
        {
            Like toCreate=new Like();
            toCreate.setId(newLike.getId());
            toCreate.setUser(user);
            toCreate.setPost(post);
            return likeRepository.save(toCreate);
        }
        return null;
    }


    public void deleteOneLikeById(Long likeId)
    {
        likeRepository.deleteById(likeId);
    }
}
