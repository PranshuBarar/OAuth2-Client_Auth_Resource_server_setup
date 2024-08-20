package com.resourceserverv4.resourceserverv4.repo;

import com.resourceserverv4.resourceserverv4.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    UserEntity findByUsername(String username);

    UserEntity findByUserId(UUID userId);
}
