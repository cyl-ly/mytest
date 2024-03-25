package com.cyl.mybatisplus.Util;

import com.cyl.mybatisplus.pojo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RequiredArgsConstructor
@Component
public class RestTemplateUtil {
    private final RestTemplate restTemplate;

    @Value("${rest.getUsers}")
    private String getUsers;

    public List<User> getUsers(){
        try {
            ResponseEntity<List<User>> responseEntity = restTemplate.exchange(getUsers, HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {});
            //返回状态码是 200
            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                List<User> result = responseEntity.getBody();       //得到数据
                if (result != null && !result.isEmpty()) {
                    return result;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
