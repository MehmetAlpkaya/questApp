package com.example.questApp.response;

import lombok.Data;

@Data
public class PostCreateRequest
{
    Long id;
    String text;
    String title;
    Long userId;
}
