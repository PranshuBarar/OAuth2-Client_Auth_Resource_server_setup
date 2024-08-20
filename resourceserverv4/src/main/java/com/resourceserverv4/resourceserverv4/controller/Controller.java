package com.resourceserverv4.resourceserverv4.controller;

import com.resourceserverv4.resourceserverv4.entities.UserEntity;
import com.resourceserverv4.resourceserverv4.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
public class Controller {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/")
    public String home() {
        LocalDateTime time = LocalDateTime.now();

        String username = getUserName();

        return "Welcome Home! This is Resource Server - " + time + "<br>" + "How are you '" + username + "'?" + " A Warm Welcome from Resource Server";
    }

    private String getUserName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.isAuthenticated()) {
            Jwt jwt = (Jwt) authentication.getPrincipal();
//            return jwt.getClaimAsString("sub");

            UUID userId = UUID.fromString(jwt.getClaimAsString("sub"));

            UserEntity userEntity = userRepository.findByUserId(userId);

            return userEntity.getUsername();

        }
        return null;
    }
}
