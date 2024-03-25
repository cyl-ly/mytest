package com.cyl.mybatisplus.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cyl.mybatisplus.pojo.User;

import java.util.List;

public interface UserService extends IService<User> {
    List<User> getUsers();
}
