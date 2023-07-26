package com.example.questApp.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.security.PrivateKey;

@Entity
@Table(name = "Post")
@Data
public class Post {
    @Id
    private Long id;
    private Long userId;
    private String title;
    private String text;


}
