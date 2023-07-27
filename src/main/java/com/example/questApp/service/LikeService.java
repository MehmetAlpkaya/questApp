package com.example.questApp.service;

import com.example.questApp.entity.Like;
import com.example.questApp.entity.Post;
import com.example.questApp.entity.User;
import com.example.questApp.repository.LikeRepository;
import org.springframework.stereotype.Service;
import com.example.questApp.requests.LikeCreateRequest;

import java.util.List;
import java.util.Optional;

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




    public List<Like> getAllLike(Optional<Long> userId, Optional<Long> postId)
    {
        if(userId.isPresent() && postId.isPresent())
        {
            return likeRepository.findAllByUserIdAndPostId(userId,postId);
        } else if (userId.isPresent())
        {
            return likeRepository.findByUserId(userId.get());

        } else if (postId.isPresent())
        {
         return likeRepository.findByPostId(postId);
        }
        else
            return likeRepository.findAll();
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
