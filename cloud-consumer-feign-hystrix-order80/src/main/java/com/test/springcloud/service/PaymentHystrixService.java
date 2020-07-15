package com.test.springcloud.service;

import com.test.springcloud.service.fallback.PaymentHystrixServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "cloud-payment-hystrix-service",fallback = PaymentHystrixServiceFallback.class)
public interface PaymentHystrixService {

    @GetMapping(value = "/payment/hystrix/ok/{id}")
    String hystrixOk(@PathVariable("id") Integer id);

    @GetMapping(value = "/payment/hystrix/timeout/{id}")
    String hystrixTimeout(@PathVariable("id") Integer id);
}
