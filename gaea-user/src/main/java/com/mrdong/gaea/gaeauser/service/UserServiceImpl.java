package com.mrdong.gaea.gaeauser.service;

import com.google.code.kaptcha.Producer;
import com.mrdong.gaea.gaeauser.constant.Constant;
import com.mrdong.gaea.gaeauser.model.*;
import com.mrdong.gaea.gaeauser.util.JwtTokenUtils;
import com.mrdong.gaea.gaeauser.util.Result;
import com.mrdong.gaea.gaeauser.util.Trace;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private Producer producer;

    @Override
    public Result login(String name) {
        Result  result = new Result();
        Trace trace = Trace.getInstance();
        UserLoginResponse response = new UserLoginResponse();

        User user = new User();

        user.setUid(111);

        Map<String,Object> map = new HashMap<>();
        map.put("uid",user.getUid());
        map.put("exp", DateTime.now().plusSeconds(40).toDate().getTime()/1000);
        String token = JwtTokenUtils.generatorToken(map);

        response.setToken(token);
        result.setData(response);

        return result;
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
        //保存到shiro session
        session.setAttribute(Constant.VERIFY_CODE, text);
        try {
            ServletOutputStream out = response.getOutputStream();
            ImageIO.write(image, "jpg", out);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public CheckAuthResponse checkAuth(CheckAuthRequest request) {
        CheckAuthResponse response = new CheckAuthResponse();

        try {
            beforeValidateAuth(request);

        }catch (Exception e){

        }


        return null;
    }

    private void beforeValidateAuth(CheckAuthRequest request){
        if (request==null){

        }
    }
}
