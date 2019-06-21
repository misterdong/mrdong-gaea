package com.mrdong.gaeasso.controller;

import com.mrdong.gaeasso.annotation.Anonymous;
import com.mrdong.gaeasso.service.ISSOService;
import com.mrdong.gaeasso.util.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/sso")
public class SSOController extends BaseController{

    @Autowired
    private ISSOService issoService;

    @PostMapping("/ssologin")
    @Anonymous
    public Result ssoLogin(String phone,String password,String verifyCode,
                           HttpServletRequest request , HttpServletResponse response){

        if (StringUtils.isEmpty(phone)){
            return Result.fail("400","手机号不能为空");
        }
        if (StringUtils.isEmpty(password)){
            return Result.fail("400","密码不能为空");
        }
        if (StringUtils.isEmpty(verifyCode)){
            return Result.fail("400","验证码不能为空");
        }

        return issoService.login(phone,password, verifyCode,request,response);
    }

    @GetMapping("/test")
    public String test(){

        return getUid();
    }
    @GetMapping(value = "/getVerifyCode")
    @Anonymous
    public void getVerifyCode(HttpServletRequest request, HttpServletResponse response){
        issoService.getVerifyCode(request,response);
    }
}
