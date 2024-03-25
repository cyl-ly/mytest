package com.cyl.mybatisplus.controller;

import com.cyl.mybatisplus.Service.UserService;
import com.cyl.mybatisplus.pojo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TestController {
    private final UserService userService;

    @GetMapping("/Rest/getUsers")
    public List<User> getUsers(){
        List<User> users = userService.getUsers();
        return users;
    }
}
