package com.test.springcloud.service.fallback;

import com.test.springcloud.service.PaymentHystrixService;
import org.springframework.stereotype.Service;

/**
 * 服务降级处理
 */
@Service
public class PaymentHystrixServiceFallback implements PaymentHystrixService {

    @Override
    public String hystrixOk(Integer id) {
        return "hystrixOk  fallback";
    }

    @Override
    public String hystrixTimeout(Integer id) {
        return "hystrixTimeout fallback";
    }
}
