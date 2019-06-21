package com.mrdong.gaea.gaeauser;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.mrdong.gaea.gaeauser.mapper")
public class GaeaUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(GaeaUserApplication.class, args);
    }


}
