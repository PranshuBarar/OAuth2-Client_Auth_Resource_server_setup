package com.clientv4.clientv4.Controller;


import com.clientv4.clientv4.Dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PostExchange;

@RestController
@RequiredArgsConstructor
public class Controller {

    private final Client client;

    @GetMapping("/")
    public String welcome(){
        String welcome = client.getWelcome();
        return "<h1>" + welcome + "</h1>";
    }

    @PostMapping("/signup")
    public String signup(@RequestBody UserDto userDto){
        return client.signup(userDto);
    }

}
