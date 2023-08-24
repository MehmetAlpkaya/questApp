package com.example.questApp.response;

import com.example.questApp.entity.Like;
import lombok.Data;

@Data
public class LikeResponse  // ı created this class for post info of like and user info of like, bc need using userId for acces to user profile
{
    // getting LikeResponse return in LikeService with getAllLike method
Long id;
Long userId;
Long postId;

    public LikeResponse(Like entity) // it'll map LikeResponse
    {
        this.id = entity.getId();
        this.userId = entity.getUser().getId();
        this.postId = entity.getPost().getId();
    }
}
