package com.test.springcloud.web;

import com.test.springcloud.entities.ResultData;
import com.test.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderFeignWeb {

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping(value = "/consumer/payment/get/{id}")
    public ResultData getPaymentById(@PathVariable("id") Long id){
        return paymentFeignService.get(id);
    }


    @GetMapping(value = "/consumer/payment/feign/timeout")
    public String getPaymentById(){
        //feign-ribbon 客户端默认等待1秒
        return paymentFeignService.getPaymentTimeout();
    }
}
