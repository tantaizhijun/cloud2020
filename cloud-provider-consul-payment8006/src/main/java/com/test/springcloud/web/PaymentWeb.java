package com.test.springcloud.web;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Slf4j
@RequestMapping(value = "/payment")
public class PaymentWeb {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/consul")
    public String get(){

        return "with consul:" + serverPort + UUID.randomUUID();
    }

}
