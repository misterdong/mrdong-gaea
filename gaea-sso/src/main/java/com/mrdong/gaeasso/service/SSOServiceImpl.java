package com.mrdong.gaeasso.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SSOServiceImpl implements ISSOService {

    @Value("${server.port}")
    String port;

    @Override
    public String login(String name) {
        return name+" 登录成功!!,port: "+port;
    }
}
