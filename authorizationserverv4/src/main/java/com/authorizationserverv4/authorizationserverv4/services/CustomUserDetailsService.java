package com.authorizationserverv4.authorizationserverv4.services;

import com.authorizationserverv4.authorizationserverv4.entities.UserEntity;
import com.authorizationserverv4.authorizationserverv4.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username);

        Set<GrantedAuthority> authorities = new HashSet<>();

        return new CustomUserDetails(userEntity,authorities);
    }
}
