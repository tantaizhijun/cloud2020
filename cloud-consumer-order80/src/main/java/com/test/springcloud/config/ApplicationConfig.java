package com.test.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfig {


    //netflix-eureka引入时自带引入netflix-ribbon, 因此可以用 LoadBalanced
    @Bean
//    @LoadBalanced  //使用自定义的负载均衡策略
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
