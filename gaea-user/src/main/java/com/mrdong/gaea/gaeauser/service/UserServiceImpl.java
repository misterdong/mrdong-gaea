package com.mrdong.gaea.gaeauser.service;

import com.mrdong.gaea.gaeauser.exception.ErrorCodes;
import com.mrdong.gaea.gaeauser.exception.GaeaUserForbiddenException;
import com.mrdong.gaea.gaeauser.mapper.UserMapper;
import com.mrdong.gaea.gaeauser.model.*;
import com.mrdong.gaea.gaeauser.util.JwtTokenUtils;
import com.mrdong.gaea.gaeauser.util.Result;
import com.mrdong.gaea.gaeauser.util.Trace;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    private static final String SHA_CODE = "misterdong-gaea";

    @Override
    public Result login(String phone,String password) {
        Result result = new Result();
        Trace trace = Trace.getInstance();
        User user = userMapper.getUserInfo(phone);

        if (user == null) {
            result.setCode("400");
            result.setMsg("no user");
            return result;
        }
        String pass = encryptPassword(password);
        if (!Objects.equals(pass,user.getPassword())){
            result.setCode("400");
            result.setMsg("password error");
            return result;
        }

        Map<String, Object> map = new HashMap<>();

        map.put("uid", user.getUid());
        map.put("exp", DateTime.now().plusSeconds(40).toDate().getTime() / 1000);
        String token = JwtTokenUtils.generatorToken(map);

        return Result.success(token);
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

    @Override
    public Result register(User user) {

        user.setPassword(encryptPassword(user.getPassword()));

        int uid = userMapper.addUser(user);

        return Result.success(uid);
    }

    private void beforeValidateAuth(String token) {
        if (StringUtils.isEmpty(token)) {
            throw new GaeaUserForbiddenException();
        }
    }

    private String encryptPassword(String password){
        BigInteger sha = null;
        byte[] bytes = password.getBytes();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(bytes);
            sha= new BigInteger(messageDigest.digest());

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return sha.toString(32);
    }
}
