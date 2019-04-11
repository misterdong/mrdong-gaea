package com.mrdong.gaea.gaeauser.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String login(String name) {
        String forObject = restTemplate.getForObject("http://GAEA-SSO/sso/ssologin?name=" + name, String.class);
        return forObject;
    }
}
