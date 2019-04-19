package com.mrdong.gaea.gaeauser.controller;

import com.mrdong.gaea.gaeauser.service.IUserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/login")
    public String login(@RequestParam("name") String name){
        return userService.login(name);
    }

    @GetMapping("/logout")
    public String logout(@RequestParam("userId")Integer userId){
        return null;
    }
}
