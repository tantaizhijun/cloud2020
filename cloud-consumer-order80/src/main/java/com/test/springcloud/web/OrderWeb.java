package com.test.springcloud.web;

import com.test.springcloud.entities.Payment;
import com.test.springcloud.entities.ResultData;
import com.test.springcloud.lb.LoadBalance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@RestController
@Slf4j
public class OrderWeb {

//    public static final String payment_URl = "http://localhost:8001";



    @Resource
    private LoadBalance loadBalance;

    @Resource
    private DiscoveryClient discoveryClient;

    //调用的服务是集群时,就需要使用服务名称了,进行负载均衡
    public static final String payment_URl = "http://CLOUD-PAYMENT-SERVICE";

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

    @GetMapping(value = "/consumer/payment/lb")
    public String getPaymentlb(){
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if(instances == null || instances.size() ==0) {
            return null;
        }
        ServiceInstance instance = loadBalance.instance(instances);
        URI uri = instance.getUri();
        return restTemplate.getForObject(uri + "/payment/lb",String.class);
    }

}
