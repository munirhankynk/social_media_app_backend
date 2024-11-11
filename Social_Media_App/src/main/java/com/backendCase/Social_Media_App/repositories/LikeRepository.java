package com.backendCase.Social_Media_App.repositories;

import com.backendCase.Social_Media_App.entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    // Check if a user has liked a specific post
    boolean existsByUserIdAndPostId(Long userId, Long postId);

    Like save(Like like);

    void deleteByUserIdAndPostId(Long userId, Long postId);

    List<Like> findByPostId(Long postId);
}