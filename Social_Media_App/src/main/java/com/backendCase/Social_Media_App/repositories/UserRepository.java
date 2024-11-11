package com.backendCase.Social_Media_App.repositories;

import com.backendCase.Social_Media_App.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}