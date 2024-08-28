package com.resourceserverv4.resourceserverv4.controller;

import com.resourceserverv4.resourceserverv4.Dto.UserDto;
import com.resourceserverv4.resourceserverv4.entities.UserEntity;
import com.resourceserverv4.resourceserverv4.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

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

            UUID userId = UUID.fromString(jwt.getClaimAsString("sub"));

            UserEntity userEntity = userRepository.findByUserId(userId);

            return userEntity.getUsername();

        }
        return null;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserDto userDto){
        UserEntity userEntity = userRepository.findByUsername(userDto.getUsername());
        if(userEntity != null){
            return new ResponseEntity<>("User already exists with this username", HttpStatus.OK);
        }
        userEntity = new UserEntity();
        userEntity.setUsername(userDto.getUsername());
        userEntity.setPassword(userDto.getPassword());

        userRepository.save(userEntity);

        return new ResponseEntity<>("User saved successfully", HttpStatus.CREATED);
    }


}
