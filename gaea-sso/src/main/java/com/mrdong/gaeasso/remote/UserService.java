package com.mrdong.gaeasso.remote;

import com.mrdong.gaeasso.util.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "gaea-user")
public interface UserService {

    @GetMapping(value = "/user/login")
    Result login(@RequestParam("phone")String phone,@RequestParam("password")String password);
}
