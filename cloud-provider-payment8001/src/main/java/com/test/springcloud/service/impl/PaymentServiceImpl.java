package com.test.springcloud.service.impl;

import com.test.springcloud.dao.PaymentDao;
import com.test.springcloud.entities.Payment;
import com.test.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    @Override
    public Long create(Payment payment) {
         paymentDao.create(payment);
        return payment.getId();
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPayment(id);
    }
}
