package com.mrdong.gaeasso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class GaeaSsoApplication {

    public static void main(String[] args) {
        SpringApplication.run(GaeaSsoApplication.class, args);
    }

}
