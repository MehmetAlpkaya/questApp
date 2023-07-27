package com.example.questApp.requests;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import lombok.Data;

@Data
public class PostUpdateRequest
{
    String title;
    @Lob
    @Column(columnDefinition="text")
    String text;


}
