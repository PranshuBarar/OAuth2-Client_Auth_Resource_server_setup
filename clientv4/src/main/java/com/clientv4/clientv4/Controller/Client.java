package com.clientv4.clientv4.Controller;

import com.clientv4.clientv4.Dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange("http://127.0.0.1:8090")
public interface Client {

    @GetExchange("/")
    String getWelcome();

    @PostExchange("/signup")
    String signup(@RequestBody UserDto userDto);

}
