package com.example.questApp.controller;

import com.example.questApp.entity.Like;
import com.example.questApp.response.LikeResponse;
import com.example.questApp.service.LikeService;
import org.springframework.web.bind.annotation.*;
import com.example.questApp.requests.LikeCreateRequest;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/likes")
public class LikeController
{
   private LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @GetMapping
    public List<LikeResponse> getLike(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> postId)
    {
        return likeService.getAllLike(userId,postId);
    }

    @GetMapping("/{likeId}")
    public Like getLikeById(@PathVariable Long likeId)
    {
        return likeService.findLikeById(likeId);
    }

    @PostMapping
    public Like createLike(@RequestBody LikeCreateRequest newLike)
    {
      return likeService.createLike(newLike);
    }
    @DeleteMapping("/{likeId}")
    public void deleteOneLike(@PathVariable Long likeId) {
        likeService.deleteOneLikeById(likeId);
    }
}
