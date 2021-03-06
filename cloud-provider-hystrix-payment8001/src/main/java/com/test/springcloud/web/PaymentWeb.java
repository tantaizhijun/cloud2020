package com.test.springcloud.web;

import com.test.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping(value = "/payment")
public class PaymentWeb {


    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;


    @GetMapping(value = "/hystrix/ok/{id}")
    public String get(@PathVariable("id") Integer id){
        log.info("00000");
        return paymentService.paymentInfo_ok(id);
    }

    @GetMapping(value = "/hystrix/timeout/{id}")
    public String gettimeout(@PathVariable("id") Integer id){
        log.info("1111");
        return paymentService.paymentInfo_timeout(id);
    }


    /**
     * 服务熔断
     * http://localhost:8001/payment/circuit/32
     * @param id
     * @return
     */
    @GetMapping("/payment/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        String result = paymentService.paymentCircuitBreaker(id);
        log.info("***result:" + result);
        return result;
    }

}
