package com.test.springcloud.web;

import com.test.springcloud.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderHystrixFeignWeb {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping(value = "/consumer/payment/hystrix/ok/{id}")
    public String getPaymentById(@PathVariable("id") Integer id){
        log.info("feign--hystrixOk");
        return paymentHystrixService.hystrixOk(id);
    }


    @GetMapping(value = "/consumer/payment/hystrix/timeout/{id}")
    public String getPaymentByIdTimeout(@PathVariable("id") Integer id){
        //调用超时的接口
        log.info("feign--hystrixTimeout");
        return paymentHystrixService.hystrixTimeout(id);
    }
}
