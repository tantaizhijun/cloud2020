package com.test.springcloud.web;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.test.springcloud.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "globalTimeoutFallback")
public class OrderHystrixFeignWeb {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @HystrixCommand
    @GetMapping(value = "/consumer/payment/hystrix/ok/{id}")
    public String getPaymentById(@PathVariable("id") Integer id){
        log.info("feign--hystrixOk");
        int i= 10/0;
        return paymentHystrixService.hystrixOk(id);
    }


    @HystrixCommand(fallbackMethod = "consumerTimeoutFallback",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")
    })
//    @HystrixCommand
    @GetMapping(value = "/consumer/payment/hystrix/timeout/{id}")
    public String getPaymentByIdTimeout(@PathVariable("id") Integer id){
        //调用超时的接口
        log.info("feign--hystrixTimeout");
        return paymentHystrixService.hystrixTimeout(id);
    }

    //服务降级处理方法
    public String consumerTimeoutFallback(@PathVariable("id") Integer id){
        return "消费者  线程池:" + Thread.currentThread().getName() +  " 超时或运行报错 handler id:" + id;
    }

    public String globalTimeoutFallback(){
        return "消费者 全局 超时或运行报错 handler ";
    }



}
