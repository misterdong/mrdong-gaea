package com.mrdong.gaea.gaeauser.controller;

import com.mrdong.gaea.gaeauser.service.IUserService;
import com.mrdong.gaea.gaeauser.util.Result;
import com.mrdong.gaea.gaeauser.util.Trace;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/login")
    public Result login(@RequestParam("name") String name){

        Trace trace = Trace.getInstance();
        trace.setValue(111);


        return userService.login(name);
    }

    @GetMapping("/logout")
    public String logout(@RequestParam("userId")Integer userId){
        return null;
    }

    @GetMapping(value = "/getVerifyCode")
    public void getVerifyCode(HttpServletRequest request, HttpServletResponse response){
        userService.getVerifyCode(request,response);
    }
}
