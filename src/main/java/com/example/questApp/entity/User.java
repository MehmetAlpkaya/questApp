package com.example.questApp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity // Bu class db ye maplenecek
@Table(name = "user")
@Data
public class User {
    @Id
    private Long id;
    private String username;
    private String password;
}
