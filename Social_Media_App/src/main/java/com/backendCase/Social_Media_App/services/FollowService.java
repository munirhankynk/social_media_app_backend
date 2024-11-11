package com.backendCase.Social_Media_App.services;

import com.backendCase.Social_Media_App.entities.Follow;
import com.backendCase.Social_Media_App.repositories.FollowRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@NoArgsConstructor
@AllArgsConstructor
public class FollowService {

    @Autowired
    private FollowRepository followRepository;

    public boolean followUser(Long followerId, Long followingId) {
        if (followRepository.existsByFollowerIdAndFollowingId(followerId, followingId)) {
            return false;
        }

        Follow follow = new Follow(followerId, followingId);

        followRepository.save(follow);
        return true;
    }

    public boolean unfollowUser(Long followerId, Long followingId) {
        Follow follow = followRepository.findByFollowerIdAndFollowingId(followerId, followingId)
                .orElseThrow(() -> new RuntimeException("Follow not found"));

        followRepository.delete(follow);
        return true;
    }
}
