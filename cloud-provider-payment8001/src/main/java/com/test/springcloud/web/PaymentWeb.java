package com.test.springcloud.web;

import com.test.springcloud.entities.Payment;
import com.test.springcloud.entities.ResultData;
import com.test.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "/payment")
public class PaymentWeb {


    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/create")
    public ResultData create(@RequestBody Payment payment){
        Long id = paymentService.create(payment);
        if(id>0) {
            return new ResultData(200,"保存成功,server port:" + serverPort,id);
        }
        return new ResultData(-1,"保存失败");
    }

    @GetMapping(value = "/get/{id}")
    public ResultData get(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        if(payment == null) {
            return new ResultData(401,"查询失败");
        }
        return new ResultData(200,"查询成功,server port:"+serverPort,payment);
    }


    //获取微服务集群的信息
    @GetMapping(value = "/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        services.forEach(e ->{
            System.out.println("-----" + e);
        });
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        instances.forEach(e -> {
            System.out.println(e.getInstanceId() + ":" + e.getHost() + ":" + e.getPort() +":"+e.getUri());
        });
        return this.discoveryClient;


    }

}
