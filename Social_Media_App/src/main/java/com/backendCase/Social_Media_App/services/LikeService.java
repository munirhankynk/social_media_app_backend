package com.backendCase.Social_Media_App.services;

import com.backendCase.Social_Media_App.entities.Like;
import com.backendCase.Social_Media_App.entities.User;
import com.backendCase.Social_Media_App.repositories.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LikeService {

    @Autowired
    private LikeRepository likeRepository;

    // Like a post
    public boolean likePost(Long userId, Long postId) {
        if (likeRepository.existsByUserIdAndPostId(userId, postId)) {
            return false;  // Already liked
        }

        likeRepository.save(new Like(userId, postId));  // Add like
        return true;
    }


    // Unlike a post
    public boolean unlikePost(Long userId, Long postId) {
        if (!likeRepository.existsByUserIdAndPostId(userId, postId)) {
            return false;  // Not liked
        }
        likeRepository.deleteByUserIdAndPostId(userId, postId);  // Remove like
        return true;
    }

    // Check if a user has liked a post
    public boolean isLiked(Long userId, Long postId) {
        return likeRepository.existsByUserIdAndPostId(userId, postId);
    }

    // Get the list of users who liked a post
    public List<User> getLikes(Long postId) {
        List<Like> likes = likeRepository.findByPostId(postId);
        List<User> users = new ArrayList<>();
        for (Like like : likes) {
            users.add(like.getUser());  // Add the users who liked the post
        }
        return users;
    }
}
