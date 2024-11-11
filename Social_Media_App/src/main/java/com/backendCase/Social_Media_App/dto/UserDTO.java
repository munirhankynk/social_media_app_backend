package com.backendCase.Social_Media_App.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Data
@Getter
@Setter
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private String fullName;
    private String profilePicture;
    private String bio;
    private Long createdAt;
    private boolean followed;
}
