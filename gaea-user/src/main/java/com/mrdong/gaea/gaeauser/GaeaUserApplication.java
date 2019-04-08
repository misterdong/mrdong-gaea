package com.mrdong.gaea.gaeauser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableEurekaClient
@RestController
@SpringBootApplication
public class GaeaUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(GaeaUserApplication.class, args);
    }

    @RequestMapping("/test")
    public String test(){
        return "test";
    }
}
