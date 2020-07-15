package com.test.springcloud.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Override
    public String paymentInfo_ok(Integer id) {
        return "线程池:" + Thread.currentThread().getName() + "paymentInfo_ok, id=" + id;
    }

    @Override
    public String paymentInfo_timeout(Integer id) {
        try {
            TimeUnit.SECONDS.sleep(3);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "线程池:" + Thread.currentThread().getName() + "paymentInfo_timeout, id=" + id;
    }
}
