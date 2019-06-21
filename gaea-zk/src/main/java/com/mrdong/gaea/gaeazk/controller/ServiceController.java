package com.mrdong.gaea.gaeazk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: liudong
 * @date: 2019-06-19 20:24
 **/
@RestController
public class ServiceController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/services")
    public List<String> getAllService(){
        return discoveryClient.getServices();
    }
    @GetMapping("/service/instance/{serviceName}")
    public Set<String> getAllServiceInstances(@PathVariable String serviceName){
        return discoveryClient.getInstances(serviceName).
                stream()
                .map(s ->
                        s.getHost()+":"+s.getPort()).collect(Collectors.toSet());
    }
}
