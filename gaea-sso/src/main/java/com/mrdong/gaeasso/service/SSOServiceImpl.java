package com.mrdong.gaeasso.service;

import com.alibaba.fastjson.JSONObject;
import com.mrdong.gaeasso.model.UserLoginResponse;
import com.mrdong.gaeasso.util.JwtTokenUtils;
import com.mrdong.gaeasso.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    public String login(String phone, String password, HttpServletResponse response) {

        log.info("login request:{}", phone);

        try {
            //TODO 验证

            Result requestReuslt = restTemplate.getForObject("http://GAEA-USER/user/login?phone=" + phone, Result.class);
            if (requestReuslt != null) {
                System.out.println(requestReuslt);
            }
            response.addHeader("Set-Cookie", "access_token=" + requestReuslt.getData().toString() + ";Path=/;HttpOnly");

        } catch (Exception e) {

        }


        return phone + " 登录成功!!";
    }

    @Override
    public String checkToken(String token, HttpServletResponse response) throws IOException {
        Result requestReuslt = restTemplate.getForObject("http://GAEA-USER/user/checkToken?token=" + token, Result.class);
        if (requestReuslt != null && "200".equals(requestReuslt.getCode())) {
            System.out.println(requestReuslt);

            return requestReuslt.getData().toString();
        } else if (requestReuslt != null){
            JSONObject errMsg = new JSONObject();
            errMsg.put(requestReuslt.getCode(),requestReuslt.getMsg());

            response.getWriter().write(errMsg.toJSONString());
        }
        return null;
    }
}
