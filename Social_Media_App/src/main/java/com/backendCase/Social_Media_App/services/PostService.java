package com.backendCase.Social_Media_App.services;

import com.backendCase.Social_Media_App.dto.PostDTO;
import com.backendCase.Social_Media_App.dto.UserDTO;
import com.backendCase.Social_Media_App.entities.Post;
import com.backendCase.Social_Media_App.entities.User;
import com.backendCase.Social_Media_App.repositories.FollowRepository;
import com.backendCase.Social_Media_App.repositories.LikeRepository;
import com.backendCase.Social_Media_App.repositories.PostRepository;
import com.backendCase.Social_Media_App.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private FollowRepository followRepository;

    public List<PostDTO> getPosts(Long userId, List<Long> postIds) {
        List<PostDTO> postDTOs = new ArrayList<>();

        List<Post> posts = postRepository.findPostsByIds(postIds);

        for (Long postId : postIds) {
            Optional<Post> postOpt = posts.stream().filter(post -> post.getId().equals(postId)).findFirst();

            PostDTO postDTO = new PostDTO();

            if (postOpt.isPresent()) {
                Post post = postOpt.get();
                postDTO.setId(post.getId());
                postDTO.setDescription(post.getDescription());
                postDTO.setImage(post.getImage());
                postDTO.setCreatedAt(post.getCreatedAt());

                User user = post.getOwner();
                UserDTO userDTO = new UserDTO();
                userDTO.setId(user.getId());
                userDTO.setUsername(user.getUsername());
                userDTO.setFullName(user.getFullName());
                userDTO.setProfilePicture(user.getProfilePicture());

                boolean followed = followRepository.existsByFollowerIdAndFollowingId(userId, user.getId());
                userDTO.setFollowed(followed);

                postDTO.setOwner(userDTO);

                boolean liked = likeRepository.existsByUserIdAndPostId(userId, post.getId());
                postDTO.setLiked(liked);

            } else {
                postDTO = new PostDTO();
            }

            postDTOs.add(postDTO);
        }

        postDTOs.sort(Comparator.comparingInt(postDTO -> postIds.indexOf(postDTO.getId())));

        return postDTOs;
    }

    public List<Post> mixByOwners(List<Post> posts) {
        Map<Long, Queue<Post>> ownerPostsMap = new LinkedHashMap<>();

        for (Post post : posts) {
            ownerPostsMap
                    .computeIfAbsent(post.getOwner().getId(), k -> new LinkedList<>())
                    .offer(post);
        }

        List<Post> mixedPosts = new ArrayList<>();
        boolean added = true;

        while (added) {
            added = false;
            for (Queue<Post> ownerPosts : ownerPostsMap.values()) {
                if (!ownerPosts.isEmpty()) {
                    mixedPosts.add(ownerPosts.poll());
                    added = true;
                }
            }
        }
        return mixedPosts;
    }
}