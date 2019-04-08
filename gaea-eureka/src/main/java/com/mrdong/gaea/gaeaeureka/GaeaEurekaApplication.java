package com.mrdong.gaea.gaeaeureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class GaeaEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(GaeaEurekaApplication.class, args);
        System.out.println("eureka 已启动");
    }

}
