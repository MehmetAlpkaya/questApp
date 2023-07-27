package com.example.questApp.repository;

import com.example.questApp.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like,Long>
{
    List<Like> findAllByUserIdAndPostId(Optional<Long> userId, Optional<Long> postId);

    List<Like> findByUserId(Long aLong);

    List<Like> findByPostId(Optional<Long> postId);
}
