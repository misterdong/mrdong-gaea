package com.mrdong.gaeasso.service;

import com.alibaba.fastjson.JSONObject;
import com.google.code.kaptcha.Producer;
import com.mrdong.gaeasso.constant.Constant;
import com.mrdong.gaeasso.model.UserLoginResponse;
import com.mrdong.gaeasso.remote.UserService;
import com.mrdong.gaeasso.util.CookieUtils;
import com.mrdong.gaeasso.util.JwtTokenUtils;
import com.mrdong.gaeasso.util.Result;
import com.mrdong.gaeasso.util.UUIDUtils;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class SSOServiceImpl implements ISSOService {

    @Value("${server.port}")
    String port;

    @Autowired
    private Producer producer;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserService userService;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Override
    public Result login(String phone, String password, String verifyCode,HttpServletRequest request,HttpServletResponse response) {

        log.info("login request:{}", phone);
        Result result = new Result();
        try {
            //TODO 验证
            String verifyCodeId = CookieUtils.getCookie(request, "verify_code");

            String code = redisTemplate.opsForValue().get("user:verifyCode:" + verifyCodeId);
            if (!Objects.equals(code,verifyCode)){
                return Result.fail("400","验证码错误");
            }
             result = userService.login(phone,password);
            if (result != null) {
                System.out.println(result);
            }
            if (Objects.equals(result.getCode(),"200")){
                response.addHeader("Set-Cookie", "access_token=" + result.getData().toString() + ";Path=/;HttpOnly");
                result.setCode("200");
                result.setMsg("登陆成功");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return result;
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

    @Override
    public void getVerifyCode(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String uri = request.getRequestURI();
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        //生成文字验证码
        String text = producer.createText();
        //生成图片验证码
        BufferedImage image = producer.createImage(text);
        String uuid = UUIDUtils.getUUID();
        CookieUtils.setCookie(response,"verify_code",uuid,60);
        //保存到shiro session
        redisTemplate.opsForValue().set("user:verifyCode:"+uuid,text,60, TimeUnit.SECONDS);
        try {
            ServletOutputStream out = response.getOutputStream();
            ImageIO.write(image, "jpg", out);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
