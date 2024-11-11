package com.backendCase.Social_Media_App.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String email;
    private String fullName;
    private String profilePicture;
    private String bio;

    public User(String username, String email, String fullName, String profilePicture, String bio) {
        this.username = username;
        this.email = email;
        this.fullName = fullName;
        this.profilePicture = profilePicture;
        this.bio = bio;
    }
}
