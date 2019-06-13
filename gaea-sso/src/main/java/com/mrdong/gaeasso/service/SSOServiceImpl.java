package com.mrdong.gaeasso.service;

import com.mrdong.gaeasso.model.UserLoginResponse;
import com.mrdong.gaeasso.util.JwtTokenUtils;
import com.mrdong.gaeasso.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class SSOServiceImpl implements ISSOService {

    @Value("${server.port}")
    String port;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String login(String name) {

        log.info("login request:",name);

        try {
            //TODO 验证

            Result requestReuslt = restTemplate.getForObject("http://GAEA-USER/user/login?name=" + name, Result.class);
            if (requestReuslt!=null){

            }


        }catch (Exception e ){

        }



        return name+" 登录成功!!,port: "+port;
    }
}
