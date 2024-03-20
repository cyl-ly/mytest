package com.cyl.mybatisplus.controller;

import com.cyl.mybatisplus.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

@RestController
public class UserController implements Serializable {
    @Autowired
    private UserMapper userMapper;


}
