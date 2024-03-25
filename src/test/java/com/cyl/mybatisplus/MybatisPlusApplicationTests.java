package com.cyl.mybatisplus;

import com.cyl.mybatisplus.mapper.UserMapper;
import com.cyl.mybatisplus.pojo.User;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootTest
@RequiredArgsConstructor
class MybatisPlusApplicationTests {

    private final UserMapper userMapper;
    private final RestTemplate restTemplate;

    @Test
    public void MapperSelect() {
            String url = "http://127.0.0.1:8000/user/get";
        User forObject = restTemplate.getForObject(url, User.class);
        System.out.println(forObject);

    }

}
