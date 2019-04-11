package com.mrdong.gaeasso.controller;

import com.mrdong.gaeasso.service.ISSOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sso")
public class SSOController {

    @Autowired
    private ISSOService issoService;

    @GetMapping("/ssologin")
    public String ssoLogin(@RequestParam("name")String name){
        return issoService.login(name);
    }

}
