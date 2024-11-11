package com.backendCase.Social_Media_App.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class PostDTO {

    private Long id;
    private String description;
    private String image;
    private Instant createdAt;
    private UserDTO owner;  // This represents the UserDTO object for the owner
    private boolean liked;  // This represents whether the user liked the post

}