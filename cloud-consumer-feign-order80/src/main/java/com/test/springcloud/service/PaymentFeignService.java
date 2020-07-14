package com.test.springcloud.service;

import com.test.springcloud.entities.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {

    @GetMapping(value = "/payment/get/{id}")
    ResultData get(@PathVariable("id") Long id);

    @GetMapping(value = "/payment/feign/timeout")
    String getPaymentTimeout();


}
