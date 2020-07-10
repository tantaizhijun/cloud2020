package com.test.springcloud.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderZKWeb {

    //zk上的服务名称
    public static final String INVOKE_URL = "http://cloud-provider-payment";


    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/zk")
    public String paymentInfo() {
        //调用zk上注册的服务payment8004的接口
        String s = restTemplate.getForObject(INVOKE_URL + "/payment/zk", String.class);
        return s;
    }


}
