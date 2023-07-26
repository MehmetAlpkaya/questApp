package com.example.questApp.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Comment")
@Data
public class Comment {
    @Id
    private Long id;

    private Long postId;
    private Long userId;

    private String text;
}
