package com.example.questApp.requests;

import lombok.Data;

@Data
public class LikeCreateRequest
{
    Long id;
    Long postId;
    Long userId;
}
