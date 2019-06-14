package com.mrdong.gaeasso.controller;

import com.mrdong.gaeasso.annotation.Anonymous;
import com.mrdong.gaeasso.service.ISSOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/sso")
public class SSOController extends BaseController{

    @Autowired
    private ISSOService issoService;

    @GetMapping("/ssologin")
    @Anonymous
    public String ssoLogin(@RequestParam("phone")String phone, @RequestParam("password")String password, HttpServletResponse response){
        return issoService.login(phone,password,response);
    }

    @GetMapping("/test")
    public String test(){

        return getUid();
    }

}
