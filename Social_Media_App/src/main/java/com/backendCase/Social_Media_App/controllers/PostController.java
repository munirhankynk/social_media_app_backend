package com.backendCase.Social_Media_App.controllers;

import com.backendCase.Social_Media_App.dto.PostDTO;
import com.backendCase.Social_Media_App.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/user/{userId}")
    public List<PostDTO> getPosts(@PathVariable Long userId, @RequestParam List<Long> postIds) {
        return postService.getPosts(userId, postIds);
    }
}
