package com.test.springcloud.web;

import com.test.springcloud.entities.Payment;
import com.test.springcloud.entities.ResultData;
import com.test.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping(value = "/payment")
public class PaymentWeb {


    @Resource
    private PaymentService paymentService;

    @PostMapping(value = "/create")
    public ResultData create(@RequestBody Payment payment){
        Long id = paymentService.create(payment);
        if(id>0) {
            return new ResultData(200,"保存成功",id);
        }
        return new ResultData(-1,"保存失败");
    }

    @GetMapping(value = "/get/{id}")
    public ResultData get(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        if(payment == null) {
            return new ResultData(401,"查询失败");
        }
        return new ResultData(200,"查询成功",payment);
    }
}
