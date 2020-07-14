package com.test.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 手写 轮训 的负载均衡实现
 * 请求次数 % 服务集群数量 = 服务的索引
 */
@Component
public class MyRoundLb implements LoadBalance {

    //统计请求次数 然后%集群数量=服务的索引
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    
    private final int getAndIncrement(){
        int current;
        int next;
        //自旋锁cas
        do{
            current = this.atomicInteger.get();
            next = current >= 2147483647 ? 0 : current+1;
        }while (!atomicInteger.compareAndSet(current, next));
        System.out.println("-----第几次访问, next:" + next);
        return next;
    }

    //通过 请求次数%集群数量= 服务索引
    @Override
    public ServiceInstance instance(List<ServiceInstance> serviceInstances) {
        int i = getAndIncrement();
        int index = i % serviceInstances.size();
        return serviceInstances.get(index);
    }
}
