package com.mrdong.gaeasso.controller;

import com.mrdong.gaeasso.annotation.Anonymous;
import com.mrdong.gaeasso.service.ISSOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/sso")
public class SSOController {

    @Autowired
    private ISSOService issoService;

    @GetMapping("/ssologin")
    @Anonymous
    public String ssoLogin(@RequestParam("name")String name){
        return issoService.login(name);
    }

}
