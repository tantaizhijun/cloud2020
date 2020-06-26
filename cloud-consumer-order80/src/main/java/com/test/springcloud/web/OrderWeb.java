package com.test.springcloud.web;

import com.test.springcloud.entities.Payment;
import com.test.springcloud.entities.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderWeb {

    public static final String payment_URl = "http://localhost:8001";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/create")
    public ResultData<Payment> create(Payment payment) {
        return restTemplate.postForObject(payment_URl + "/payment/create",payment,ResultData.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public ResultData<Payment> get(@PathVariable("id") Long id) {
        return restTemplate.getForObject(payment_URl + "/payment/get/" + id,ResultData.class);
    }


}
