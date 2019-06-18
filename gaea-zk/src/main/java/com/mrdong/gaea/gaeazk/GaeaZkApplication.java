package com.mrdong.gaea.gaeazk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GaeaZkApplication {

    public static void main(String[] args) {
        SpringApplication.run(GaeaZkApplication.class, args);
    }

}
