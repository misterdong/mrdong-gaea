package com.mrdong.gaea.gaeauser.controller;

import com.mrdong.gaea.gaeauser.model.User;
import com.mrdong.gaea.gaeauser.service.IUserService;
import com.mrdong.gaea.gaeauser.util.Result;
import com.mrdong.gaea.gaeauser.util.Trace;
import org.apache.commons.lang.StringUtils;
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
    public Result login(@RequestParam("phone") String phone,@RequestParam("password") String password){

        Trace trace = Trace.getInstance();


        return userService.login(phone,password);
    }

    @GetMapping("/logout")
    public String logout(@RequestParam("userId")Integer userId){
        return null;
    }

    @PostMapping("/register")
    public Result registerUser(@RequestBody User user){

        if (StringUtils.isEmpty(user.getName())){
            return Result.fail("400","用户名不能为空");
        }
        if (StringUtils.isEmpty(user.getPhone())){
            return Result.fail("400","用户名不能为空");
        }
        if (StringUtils.isEmpty(user.getPassword())){
            return Result.fail("400","用户名不能为空");
        }
        return userService.register(user);
    }


    @GetMapping("/checkToken")
    public Result checkToken(@RequestParam("token")String token){
        return userService.checkToken(token);
    }



}
