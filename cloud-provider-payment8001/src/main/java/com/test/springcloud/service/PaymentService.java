package com.test.springcloud.service;

import com.test.springcloud.entities.Payment;

public interface PaymentService {

    Long create(Payment payment);
    Payment getPaymentById(Long id);
}
