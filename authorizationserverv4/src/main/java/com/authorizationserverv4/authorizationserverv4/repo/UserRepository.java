package com.authorizationserverv4.authorizationserverv4.repo;

import com.authorizationserverv4.authorizationserverv4.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    UserEntity findByUsername(String username);
}
