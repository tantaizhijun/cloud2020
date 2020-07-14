package com.test.springcloud.web;

import com.test.springcloud.entities.Payment;
import com.test.springcloud.entities.ResultData;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import com.test.springcloud.service.PaymentService;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping(value = "/payment")
public class PaymentWeb {


    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/create")
    public ResultData create(@RequestBody Payment payment){
        Long id = paymentService.create(payment);
        if(id>0) {
            return new ResultData(200,"保存成功,server port:"+serverPort,id);
        }
        return new ResultData(-1,"保存失败");
    }

    @GetMapping(value = "/get/{id}")
    public ResultData get(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        if(payment == null) {
            return new ResultData(401,"查询失败");
        }
        return new ResultData(200,"查询成功,server port:"+serverPort,payment);
    }

    @GetMapping(value = "/lb")
    public String getPaymentLB(){
        return serverPort;

    }
}
