package com.mrdong.gaea.gaeauser.service;

import com.google.code.kaptcha.Producer;
import com.mrdong.gaea.gaeauser.constant.Constant;
import com.mrdong.gaea.gaeauser.exception.ErrorCodes;
import com.mrdong.gaea.gaeauser.exception.GaeaUserForbiddenException;
import com.mrdong.gaea.gaeauser.exception.HttpStatusCodes;
import com.mrdong.gaea.gaeauser.mapper.UserMapper;
import com.mrdong.gaea.gaeauser.model.*;
import com.mrdong.gaea.gaeauser.util.JwtTokenUtils;
import com.mrdong.gaea.gaeauser.util.Result;
import com.mrdong.gaea.gaeauser.util.Trace;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.SignatureException;
import org.apache.commons.lang.StringUtils;
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

    @Autowired
    private UserMapper userMapper;

    @Override
    public Result login(String phone) {
        Result result = new Result();
        Trace trace = Trace.getInstance();
        User user = userMapper.getUserInfo(phone);

        if (user == null) {
            result.setCode("400");
            result.setMsg("no user");
            return result;
        }
        Map<String, Object> map = new HashMap<>();

        map.put("uid", user.getUid());
        map.put("exp", DateTime.now().plusSeconds(40).toDate().getTime() / 1000);
        String token = JwtTokenUtils.generatorToken(map);

        return Result.success(token);
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
    public Result checkToken(String token) {
        Result result = new Result();
        try {
            beforeValidateAuth(token);
            Claims claims = JwtTokenUtils.phaseToken(token);
            String uid = claims.get("uid").toString();
            result.setCode("200");
            result.setData(uid);

        } catch (GaeaUserForbiddenException e) {
            result.setCode(ErrorCodes.USER_FORBIDDEN.getErrorCode());
            result.setMsg(ErrorCodes.USER_FORBIDDEN.getErrorMessage());
        } catch (ExpiredJwtException e){
            result.setCode(ErrorCodes.TOKEN_EXPIRED.getErrorCode());
            result.setMsg(ErrorCodes.TOKEN_EXPIRED.getErrorMessage());
        } catch (SignatureException e){
            result.setCode(ErrorCodes.SIGNATURE_ERROE.getErrorCode());
            result.setMsg(ErrorCodes.SIGNATURE_ERROE.getErrorMessage());
        }


        return result;
    }

    private void beforeValidateAuth(String token) {
        if (StringUtils.isEmpty(token)) {
            throw new GaeaUserForbiddenException();
        }
    }
}
