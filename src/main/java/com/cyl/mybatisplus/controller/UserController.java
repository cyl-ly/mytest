package com.cyl.mybatisplus.controller;

import com.cyl.mybatisplus.Service.UserService;
import com.cyl.mybatisplus.Util.RestTemplateUtil;
import com.cyl.mybatisplus.pojo.User;
import com.cyl.mybatisplus.pojo.result.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController implements Serializable {

    private final UserService userService;
    private final RestTemplateUtil restTemplateUtil;
    private final RestTemplate restTemplate;

    /**
     * 正常请求得到的方法
     * @return
     */
    @GetMapping("/user/get")
    public Result<List<User>> getUsers(){
        List<User> users = userService.getUsers();
        if(users == null){
            return Result.error("获取用户列表失败");
        }
        return Result.success(users);
    }

    /**
     * 测试 RestTemplate 发送请求   需要原来的不封装到Result中
     * @return
     */
    @GetMapping("/user/get/rest")
    public Result<List<User>> getUsersByRest(){
        List<User> users = restTemplateUtil.getUsers();
        if(users == null)
            return Result.error("rest获取用户列表失败");
        return Result.success(users);
    }




}
