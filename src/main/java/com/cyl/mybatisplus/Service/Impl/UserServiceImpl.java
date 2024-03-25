package com.cyl.mybatisplus.Service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cyl.mybatisplus.Service.UserService;
import com.cyl.mybatisplus.mapper.UserMapper;
import com.cyl.mybatisplus.pojo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    private final UserMapper userMapper;

    @Override
    public List<User> getUsers() {
        List<User> users = userMapper.selectList(null);
        return users;
    }
}
